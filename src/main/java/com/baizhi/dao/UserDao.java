package com.baizhi.dao;

import com.baizhi.entity.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.Map;

public interface UserDao {
    //查询所有
    List<User> selectAllUser(@Param("begin")Integer begin,@Param("end")Integer end);

    void updateStatus(User user);

    Integer countUser();

    List<User> selectAll();

    User selectByPhone(String phone);

    void  insert(User user);
    List<Map<String, Object>> countRegistUserByMonth();

    List<Map<String, Object>> userDistributionByGender(String gender);

    Integer userDistributionMaxCount();
}
