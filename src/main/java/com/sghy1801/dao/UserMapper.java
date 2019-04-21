package com.sghy1801.dao;

import com.sghy1801.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wrm
 * @create 2019-04-21 19:21
 */
public interface UserMapper {
    //根据用户名判断是否存在用户
    User getUserExist(@Param("username") String username);
    //根据用户名返回用户
    User getUser(@Param("username") String username);
}
