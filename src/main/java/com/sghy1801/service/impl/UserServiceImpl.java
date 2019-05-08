package com.sghy1801.service.impl;

import com.sghy1801.dao.UserMapper;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(readOnly = true)
    public User login(String username, String password) {
        User user = userMapper.login(username, password);
        //匹配密码
        if (null != user) {
            if (!user.getPassword().equals(password))
                user = null;
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> listUser(int currentPage,String username,String phone) {
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


    @Transactional(readOnly = true)
    public int countUser(String username,String phone) {
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addUser(User u) {
        return userMapper.addUser(u);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUser(User u) {
        return userMapper.updateUser(u);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public Map getDate() {
        System.out.println(userMapper.getDate());
        return userMapper.getDate();
    }
}
