package com.baizhi.service;

import com.baizhi.annotation.AopAnnotation;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Repository
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    @Override
//    @AopAnnotation
    public List<Admin> queryAll() {
        return adminDao.selectAll();
    }
    @Override
    public Map<String,Object> login(String clientCode, String username, String password , HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        String serverCode = (String)session.getAttribute("serverCode");
        Admin admin = adminDao.selectByName(username);
        if(!clientCode.equals(serverCode)){
            map.put("code","300");
            map.put("message","验证码错误");
        }else if(admin == null){
            map.put("code","400");
            map.put("message","账户不存在");
        }else if(!admin.getPassword().equals(password)){
            map.put("code","500");
            map.put("message","密码错误");
        }else{
            map.put("code","200");
            map.put("message","登录成功");
            session.setAttribute("admin",admin);
        }
        return map;
    }

    @Override
    public Admin queryOne(String name) {
        return adminDao.selectByName(name);
    }

    @Override
    public void regist(Admin admin) {
        adminDao.insert(admin);
        throw new RuntimeException("演示异常");
    }
}
