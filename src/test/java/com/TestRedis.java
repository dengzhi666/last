package com;

import com.baizhi.CmfzZhenglApplication;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {CmfzZhenglApplication.class})
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AdminService adminService;

//    @Test
//    public void m1(){
//        String name = stringRedisTemplate.opsForValue().get("name");
//        System.out.println(name);
//
//    }

    @Test
    public void m2(){
        List<Admin> admins = adminService.queryAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }
    @Test
    public void m3(){
        Admin admin = adminService.queryOne("admin");
        System.out.println(admin);

    }
    @Test
    public void m4(){
       Admin admin =  new Admin("4","admin4","admin2");
        adminService.regist(admin);
    }

}
