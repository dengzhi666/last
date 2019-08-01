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
    @Autowired
    ArticleReposiroty articleReposiroty;

    @Test
    public void m2(){
        List<Article> articles = articleService.findByNameAndHighLight("故事", 1, 1);
        System.out.println(articles);
    }


    @Test
    public void m1(){
        articleReposiroty.save(new Article(null,"6","西游记","吴承恩","一个和尚和三个徒弟西天取经的故事",new Date()));
    }
}
