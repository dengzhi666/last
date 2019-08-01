package com.baizhi;

import com.baizhi.CmfzZhenglApplication;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@SpringBootTest(classes = {CmfzZhenglApplication.class})
@RunWith(SpringRunner.class)
public class Testadmin {
    @Autowired
    private AdminService adminService;

    @Test
    public void m1(){
        List<Admin> admins = adminService.queryAll();
        for (Admin admin : admins) {
            System.out.println(admin);
            System.out.println("_______________________________________");
        }
    }

}
