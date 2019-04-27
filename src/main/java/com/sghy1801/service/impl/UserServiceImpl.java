package com.sghy1801.service.impl;

import com.sghy1801.dao.UserMapper;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(String username, String password) {
        User user =userMapper.login(username,password);
        //匹配密码
        if(null != user){
            if(!user.getPassword().equals(password))
                user = null;
        }
        return user;
    }

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }
}
