package com.sghy1801.dao;

import com.sghy1801.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(@Param("username")String username,@Param("password")String password);

    /**
     * 获取所有用户
     * @return
     */
    public List<User> userList();



}
