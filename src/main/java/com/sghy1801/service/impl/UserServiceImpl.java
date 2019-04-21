package com.sghy1801.service.impl;

import com.sghy1801.dao.UserMapper;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wrm
 * @create 2019-04-21 19:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean getUserExist(String username) {
        User user = userMapper.getUserExist(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getUser(String username, String password) {
        User user = userMapper.getUser(username);
        if (user == null) {
            return 0;
        } else if (!user.getPassword().equals(password)) {
            return 1;
        } else {
            return 2;
        }
    }


}
