package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
public interface ArticleService {
    void addArticle(Article artilce);
    Map<String,Object> queryAll(Integer page, Integer rows);
    void modifyArticle(Article article);
    void removeArticle(String id);
    Article queryOne(String id);

    //---------Es---------
    String addArticleEs(Article article);
    List<Article> findByNameAndHighLight(String name , int Page , int size);
}
