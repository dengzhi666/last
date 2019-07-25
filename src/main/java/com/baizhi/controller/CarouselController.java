package com.baizhi.controller;


import com.baizhi.entity.Carousel;
import com.baizhi.entity.CarouselSto;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;


    @RequestMapping("/queryAll")
    @ResponseBody
    public CarouselSto queryAllCarousel(Integer page, Integer rows){
        CarouselSto  carouselSto = carouselService.queryAll(page, rows);
        return carouselSto;
    }
//  -----------------------jqGrid插件--------------------------
    @RequestMapping("/edit")
    @ResponseBody
    public void editCarousel(Carousel carousel, String oper, HttpSession session){
        System.out.println("进入了carousel中------");
        if("add".equals(oper)){
            System.out.println("进入了carousel的添加中------");
        }else if("edit".equals(oper)){
        }else{
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addCarousel(Carousel carousel, HttpSession session,MultipartFile img){
        System.out.println(carousel);
        System.out.println("文件名"+img.getOriginalFilename());
            String name  = img.getOriginalFilename();
            String realpath = session.getServletContext().getRealPath("/upload");
            try {
                img.transferTo(new File(realpath+"/"+name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            carousel.setImgpath(name);
            carousel.setId(UUID.randomUUID().toString());
            carouselService.addCarousel(carousel);

    }

}
