package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AdminService;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.el.ImportHandler;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows,String albumId){
        Map<String, Object> map = chapterService.queryAll(page, rows,albumId);
        return map;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public void editChapter( String oper, HttpSession session){
        if("add".equals(oper)){
            System.out.println("进入了carousel的添加中------");
        }else if("edit".equals(oper)){
        }else{
        }
    }
    @RequestMapping("upload")
    @ResponseBody
    public void addChapter(Chapter chapter, MultipartFile img, HttpSession session){
        // 给文件名加 时间戳
        String name = new Date().getTime()+img.getOriginalFilename();
        //取文件夹的真实路径
        String realpath = session.getServletContext().getRealPath("/upload");
        try {
            img.transferTo(new File(realpath+"/"+name));
            chapter.setDownpath(name);
            chapter.setId(UUID.randomUUID().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        chapterService.addChapter(chapter);
    }
    @RequestMapping("download")
    @ResponseBody
    public void download(String filename , HttpSession session, HttpServletResponse response){
        System.out.println("要下载的文件名是"+filename);

        //取文件夹的真实路径
        String realpath = session.getServletContext().getRealPath("/upload");
        byte[] bs = null;
        ServletOutputStream os = null;
        try {
          bs =   FileUtils.readFileToByteArray(new File(realpath+"/"+filename));
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
            response.setContentType("image/jpeg");
            os = response.getOutputStream();
            os.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
