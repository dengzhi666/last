package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String,Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();
        Integer records = articleDao.countArticle();
        Integer total = records % rows == 0 ? (records / rows) : (records / rows + 1);

        Integer begin = (page-1)*rows+1;
        Integer end = page*rows;
        List<Article> list = articleDao.selectAllArticle(begin, end);
        map.put("page",page); //存入当前页数
        map.put("records",records); //数据总条数
        map.put("total",total);//存入总页数
        map.put("rows",list);//存入总页数

        return map;
    }

    @Override
    public void addArticle(Article article){
        article.setId(UUID.randomUUID().toString());
        articleDao.insertArticle(article);
    }

    @Override
    public void modifyArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void removeArticle(String id) {
        articleDao.deleteArticle(id);
    }

    @Override
    public Article queryOne(String id) {
        return articleDao.selectOneArticle(id);
    }
}
