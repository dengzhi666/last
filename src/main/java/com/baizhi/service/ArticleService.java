package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;
public interface ArticleService {
    void addArticle(Article artilce);
    Map<String,Object> queryAll(Integer page, Integer rows);
    void modifyArticle(Article article);
    void removeArticle(String id);
    Article queryOne(String id);
}
