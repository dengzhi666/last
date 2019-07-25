package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("img")
    public void code(HttpServletResponse response, HttpSession session)throws Exception{
        // 1 调工具类，获取随机验证码
        String code = SecurityCode.getSecurityCode();
        // 2 往sessioin中存验证码
        session.setAttribute("serverCode", code);
        // 3 调工具类，根据生成的验证码，生成图片
        BufferedImage img = SecurityImage.createImage(code);
        // 43 把图片 输出到 client
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(img, "jpeg", out);
    }

}
