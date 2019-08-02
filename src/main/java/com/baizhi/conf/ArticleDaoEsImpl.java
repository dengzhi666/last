package com.baizhi.conf;

import com.baizhi.entity.Article;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoEsImpl implements ArticleDaoEs {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public List<Article> findByNameAndHighLight(String name, int page, int size) {
        HighlightBuilder.Field nameField = new HighlightBuilder
                .Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>").requireFieldMatch(false);


        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()

                .withQuery(QueryBuilders.multiMatchQuery(name, "title", "content", "author"))
                .withPageable(PageRequest.of(page, size))
                .withHighlightFields(nameField)
                .build();

        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(nativeSearchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                SearchHits searchHits = response.getHits();
                SearchHit[] hits = searchHits.getHits();
                ArrayList<Article> articles = new ArrayList<Article>();
                for (SearchHit hit : hits) {
                    Article article = new Article();
                    //原始map
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    article.setId(sourceAsMap.get("id").toString());
                    article.setGuruId(sourceAsMap.get("guruId").toString());
                    article.setAuthor(sourceAsMap.get("author").toString());
                    article.setContent(sourceAsMap.get("content").toString());
                    article.setTitle(sourceAsMap.get("title").toString());
                    String publishTime = sourceAsMap.get("publishTime").toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date parse = simpleDateFormat.parse(publishTime);
                        article.setPublishTime(parse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.get("title") != null) {
                        String nameHighlight = highlightFields.get("title").getFragments()[0].toString();
                        article.setTitle(nameHighlight);
                    }
                    if (highlightFields.get("content") != null) {
                        String contentHighlight = highlightFields.get("content").getFragments()[0].toString();
                        article.setContent(contentHighlight);
                    }
                    if (highlightFields.get("author") != null) {
                        String contentHighlight = highlightFields.get("author").getFragments()[0].toString();
                        article.setAuthor(contentHighlight);
                    }
                    articles.add(article);
                }
                return new AggregatedPageImpl<T>((List<T>) articles);
            }
        });
        return articles.getContent();
    }

    @Override
    public Integer findByNameAndHighlightAndPageableRecords(String name) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(name,"title","content","author"))
                .build();
        List<Article> articles1 = elasticsearchTemplate.queryForList(nativeSearchQuery, Article.class);
        return articles1.size();
    }
}
