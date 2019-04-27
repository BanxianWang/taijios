package com.sghy1801.service;

import com.sghy1801.entity.User;


import java.util.List;


public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password);

    /**
     * 获取所有用户
     * @return
     */
    public List<User> userList();


}
