package com.baizhi.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String, Object> queryAllUser(Integer page, Integer rows) {
        return userService.queryAll(page, rows);
    }

    @RequestMapping("update")
    @ResponseBody
    public void modifyStatus(String id, String status) {
        userService.modifyUser(id, status);
    }

    @RequestMapping("outputUser")
    @ResponseBody
    public void outputUser(HttpServletResponse response, HttpSession session) throws Exception {
        List<User> users = userService.queryAllUser();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户", "用户表"), User.class, users);
        //设置响应类型
        response.setContentType("application/x-xls");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("默认用户表.xls", "utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
    }

    @RequestMapping("inputUser")
    @ResponseBody
    public void inputUser(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //获取一共有多少行数据
        int lastRowNum = sheet.getLastRowNum();
        //创建一个集合去接受
        List<User> users = new ArrayList<>();
        for (int i = 0; i < lastRowNum - 1; i++) {
            User user = new User();
            Row row = sheet.getRow(i + 2);

            Cell cell1 = row.getCell(0);
            String id = cell1.getStringCellValue();
            user.setId(id);
            Cell cell2 = row.getCell(1);
            String phone = cell2.getStringCellValue();
            user.setPhone(phone);
            Cell cell3 = row.getCell(2);
            String password = cell3.getStringCellValue();
            user.setPassword(password);
            Cell cell4 = row.getCell(3);
            String salt = cell4.getStringCellValue();
            user.setSalt(salt);
            Cell cell5 = row.getCell(4);
            String dharmaname = cell5.getStringCellValue();
            user.setDharmaname(dharmaname);
            Cell cell6 = row.getCell(5);
            String province = cell6.getStringCellValue();
            user.setProvince(province);
            Cell cell7 = row.getCell(6);
            String city = cell7.getStringCellValue();
            user.setCity(city);
            Cell cell8 = row.getCell(7);
            String gender = cell8.getStringCellValue();
            user.setGender(gender);
            Cell cell9 = row.getCell(8);
            String personalSign = cell9.getStringCellValue();
            user.setPersonalSign(personalSign);
            Cell cell10 = row.getCell(9);
            String profile = cell10.getStringCellValue();
            user.setProfile(profile);
            Cell cell11 = row.getCell(10);
            String status = cell11.getStringCellValue();
            user.setStatus(status);
            Cell cell12 = row.getCell(11);
            String registTime = cell12.getStringCellValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(registTime);
            user.setRegistTime(parse);
            users.add(user);
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    // --------------------------------------用户前台----------------------------------------------------
    @ResponseBody
    @RequestMapping("login")
    public Object login(String phone, String password, HttpSession session) {
        try {
            User login = userService.login(phone, password, session);
            return login;
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", -200);
            map.put("message", e.getMessage());
            return map;
        }
    }

    @RequestMapping("regist")
    @ResponseBody
    public Object regist(User user) {
        try {
            User regist = userService.regist(user);
            return regist;
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", -400);
            map.put("message", e.getMessage());
            return map;
        }
    }

    @RequestMapping("countRegist")
    @ResponseBody
    public Object countRegist() {
        Map<String, List<String>> map = userService.countRegist();
        return map;
    }

    @RequestMapping("userDistribution")
    @ResponseBody
    public Object userDistribution() {
        Map<String, Object> map = userService.userDistribution();
        return map;
    }
}