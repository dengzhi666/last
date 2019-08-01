package com.baizhi;

        import com.baizhi.conf.ArticleReposiroty;
        import com.baizhi.entity.Article;
        import com.baizhi.service.ArticleService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.Date;
        import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CmfzZhenglApplication.class})
public class TestArticleReposiroty {
    @Autowired
    private ArticleService articleService;


    @Test
    public void m2(){
        List<Article> articles = articleService.findByNameAndHighLight("故事", 0, 3);
        System.out.println(articles);
    }


    @Test
    public void m1(){
        articleService.addArticleEs(new Article(null,"1","西游记","吴承恩","一个和尚和三个徒弟西天取经的故事",new Date()));
        articleService.addArticleEs(new Article(null,"2","水浒传","施耐庵","108将梁山聚义，然后又招安的故事",new Date()));
        articleService.addArticleEs(new Article(null,"3","三国演义","罗贯中","三国时期烽烟四起，群侯争霸的故事",new Date()));
        articleService.addArticleEs(new Article(null,"4","红楼梦","曹雪芹","贾宝玉和林黛玉悲惨爱情 的故事",new Date()));
    }
}
