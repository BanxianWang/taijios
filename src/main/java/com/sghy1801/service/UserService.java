package com.sghy1801.service;

import com.sghy1801.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wrm
 * @create 2019-04-21 19:28
 */
public interface UserService {
    //根据用户名判断是否存在用户
    public boolean getUserExist(String username);

    /**
     * 根据用户名返回用户
     * @param username
     * @return 0代表登录失败没有该用户 1代表登录失败密码错误 2代表登录成功
     */
    public int getUser(String username,String passwrod);


}
