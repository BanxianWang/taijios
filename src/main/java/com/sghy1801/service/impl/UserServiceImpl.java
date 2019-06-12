package com.sghy1801.service.impl;

import com.sghy1801.dao.UserMapper;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //用户登录验证
    @Override
    @Transactional(readOnly = true)
    public User login(String phone, String password) {
        User user = userMapper.login(phone, password);

       //匹配密码
        if (user == null) {
            throw new RuntimeException("用户名不存在！");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }
        return user;
    }

    //获取用户信息列表
    @Transactional(readOnly = true)
    public List<User> listUser(int currentPage, String username, String phone) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (username != null && !"".equals(username)) {
            param.put("username", username);
        }
        if (phone != null && !"".equals(phone)) {
            param.put("phone", phone);
        }
        param.put("start", (currentPage - 1) * 4);
        List<User> list = userMapper.listUser(param);
        return list;
    }

    //获取用户数量
    @Transactional(readOnly = true)
    public int countUser(String username, String phone) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (username != null && !"".equals(username)) {
            param.put("username", username);
        }
        if (phone != null && !"".equals(phone)) {
            param.put("phone", phone);
        }
        int count = userMapper.countUser(param);
        return count;
    }

    //新增用户
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addUser(User u) {
        return userMapper.addUser(u);
    }

    //删除用户
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    //更新用户信息
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUser(User u) {
        return userMapper.updateUser(u);
    }

    //根据用户id寻找用户
    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    //获取用户注册时间
    @Override
    public List<Map> getDate() {
        return userMapper.getDate();
    }

    //更改用户的状态
    @Override
    public int updateChanges(int userId, int changestate) {
        return userMapper.updateChanges(userId, changestate);
    }

    //更改用户的信息
    @Override
    public int updatePass(int id,String repwd,String username,String email,String address,String phone) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("repwd", repwd);
        param.put("username", username);
        param.put("email", email);
        param.put("address", address);
        param.put("phone", phone);
        return userMapper.updatePass(param);
    }

    //获取用户的所在省份
    @Override
    public List<Map> getLocalDistribution() {
        return userMapper.getLocalDistribution();
    }

    //获取当前年份新增用户的数量
    @Override
    public int getNowYearcount() {
        Map<String,Object> map = new HashMap<String,Object>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        map.put("time",sdf.format(date));
        return userMapper.countUser(map);

    }
}
