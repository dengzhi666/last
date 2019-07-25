package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object>  queryAllCarousel(Integer page, Integer rows){
        Map<String, Object> map = articleService.queryAll(page, rows);
        return map;
    }

//  -----------------------jqGrid插件--------------------------
    @RequestMapping("/edit")
    @ResponseBody
    public void editAlbum(Article article,String oper, HttpSession session,String[] id){

        if("add".equals(oper)){  //添加操作

        }

        else if("edit".equals(oper)){ //修改操作
            articleService.modifyArticle(article);
        }
        else{
            for (String s : id) {    //删除操作
                articleService.removeArticle(s);
            }
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String , Object> upload(MultipartFile file, HttpServletRequest request) {
        String originalFilename = file.getOriginalFilename();
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file1 = new File(articlePic);
        if (!file1.exists()){
            file1.mkdir();
        }
        Map<String , Object> map = new HashMap<>();

        try {
            file.transferTo(new File(articlePic,originalFilename));
            map.put("error",0);
            map.put("url","http://localhost:9000/cmfz/articlePic/"+originalFilename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("url","http://localhost:9000/cmfz/articlePic/"+originalFilename);
        return null;
    }
    }
    @RequestMapping("showAll")
    @ResponseBody
    public Map<String , Object> showAll(HttpServletRequest request){
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file = new File(articlePic);
        String[] list = file.list();
        Map<String , Object> map = new HashMap<>();
        map.put("current_url","http://localhost:9000/cmfz/articlePic/");
        map.put("total_count",list.length);
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
            Map<String , Object> map1 = new HashMap<>();
            map1.put("is_dir",false);
            map1.put("has_file",false);
            File file1 = new File(articlePic,s);
            long length = file1.length();
            map1.put("filesize",length);
            map1.put("is_photo",true);
            String substring = s.substring(s.lastIndexOf(".") + 1);
            map1.put("filetype",substring);
            map1.put("filename",s);
            map1.put("datetime",new Date());
            lists.add(map1);
        }
        map.put("file_list",lists);
        return map;
    }


    @RequestMapping("addArticle")
    @ResponseBody
    public void addArticle(Article article){
        articleService.addArticle(article);
    }

    @RequestMapping("queryOne")
    @ResponseBody
    public Article queryOneArticle(String id){
        return  articleService.queryOne(id);
    }
}
