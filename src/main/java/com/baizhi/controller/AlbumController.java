package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.CarouselSto;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;


    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object>  queryAllCarousel(Integer page, Integer rows){
        Map<String, Object> map = albumService.queryAll(page, rows);
        return map;
    }
//  -----------------------jqGrid插件--------------------------
    @RequestMapping("/edit")
    @ResponseBody
    public void editAlbum( String oper, HttpSession session){
        System.out.println("进入了carousel中------");
        if("add".equals(oper)){
            System.out.println("进入了carousel的添加中------");
        }else if("edit".equals(oper)){
            System.out.println("进入了carousel的修改中------");
        }else{
            System.out.println("进入了carousel的删除中------");
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAlbum(Album album, HttpSession session, MultipartFile img){
        String str = new Date().getTime()+"";
        String name  = str+img.getOriginalFilename();
        album.setCover(name);
            String realpath = session.getServletContext().getRealPath("/upload");
            try {
                img.transferTo(new File(realpath+"/"+name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        albumService.addAlbum(album);
    }

}
