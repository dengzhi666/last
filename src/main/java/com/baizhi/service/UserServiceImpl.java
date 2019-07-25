package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAllUser() {
        return userDao.selectAll();
    }

    @Override
    public void modifyUser(String id,String status) {
        User user = new User() ;
        if("正常".equals(status)){
            user.setId(id);
            user.setStatus("冻结");
        }else{
            user.setId(id);
            user.setStatus("正常");
        }
        userDao.updateStatus(user);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();
        Integer records = userDao.countUser();
        Integer total = records % rows==0 ? records/rows : records/rows+1;

        Integer begin = (page-1)*rows+1;
        Integer end = page * rows;
        List<User> users = userDao.selectAllUser(begin, end);

        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",users);
        return map;
    }
    // 用户前台
    @Override
    public User login(String phone, String password, HttpSession session) {
        User user = userDao.selectByPhone(phone);
        if (user == null) throw new RuntimeException("该手机号未注册！");
        if (!password.equals(user.getPassword()))  throw new RuntimeException("密码错误！");
        return user;
    }

    @Override
    public User regist(User user) {
        User selectUser = userDao.selectByPhone(user.getPhone());
        if (selectUser != null) throw new RuntimeException("该手机号已注册");
        user.setId(UUID.randomUUID().toString());
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String md5Hex = DigestUtils.md5Hex(user.getPassword() + salt);
        user.setPassword(md5Hex);
        user.setStatus("正常");
        user.setRegistTime(new Date());
        userDao.insert(user);
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-6c8fedfaac52425286c22b50d4ba6770");
        goEasy.publish("user_regist", "addOne");
        return user;
    }

    @Override
    public Map<String, List<String>> countRegist() {
        List<String> months = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        List<Map<String, Object>> list = userDao.countRegistUserByMonth();
        for (Map<String, Object> map : list) {
            months.add(map.get("MONTH").toString());
            counts.add(map.get("COUNT").toString());
        }
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("months", months);
        map.put("counts", counts);
        return map;
    }

    @Override
    public Map<String, Object> userDistribution() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> men = userDao.userDistributionByGender("男");
        List<Map<String, Object>> women = userDao.userDistributionByGender("女");
        Integer maxCount = userDao.userDistributionMaxCount();
        map.put("men", men);
        map.put("women", women);
        map.put("maxCount", maxCount);
        return map;
    }
}
