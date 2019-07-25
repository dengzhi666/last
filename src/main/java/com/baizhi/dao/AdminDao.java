package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdminDao {
    //查询所有
    List<Admin> selectAll();

    Admin selectByName(String username);

    void insert(Admin admin);
}
