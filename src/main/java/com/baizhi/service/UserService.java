package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    void modifyUser(String id,String status);
     Map<String,Object> queryAll(Integer page, Integer rows);
     List<User> queryAllUser();


    User login(String phone, String password, HttpSession session) ;
    User regist(User user) ;
    Map<String,List<String>> countRegist();
    Map<String, Object> userDistribution();
}
