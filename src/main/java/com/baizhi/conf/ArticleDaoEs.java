package com.baizhi.conf;

import com.baizhi.entity.Article;
import java.util.List;

public interface ArticleDaoEs {
    List<Article> findByNameAndHighLight(String name , int page , int size);
}
