package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    void insertArticle(Article article);

    Integer countArticle();

    List<Article> selectAllArticle(@Param("begin") Integer begin, @Param("end") Integer end);

    void updateArticle(Article article);

    void deleteArticle(String id);

    Article selectOneArticle(String id);

}
