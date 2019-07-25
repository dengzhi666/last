package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Admin> queryAll();

    Admin queryOne(String name);

    void regist(Admin admin);

   Map<String,Object> login(String clientCode, String username, String password , HttpSession session);
}
