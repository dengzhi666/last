package com.baizhi.conf;

import com.baizhi.entity.Article;
import java.util.List;

public interface ArticleDaoEs {
    // 高亮并分页
    List<Article> findByNameAndHighLight(String name , int page , int size);
    // 高亮的总页数
    Integer findByNameAndHighlightAndPageableRecords(String name);
}
