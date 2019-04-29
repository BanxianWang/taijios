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
     * 查询用户列表页
     * @param currentPage
     * @return
     */
    public List<User> listUser(int currentPage);

    /**
     * 查询用户记录数
     * @return
     */
    public int countUser();

    /**
     * 添加用户
     * @param u
     * @return
     */
    public int addUser(User u);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public int deleteUser(int id);

    /**
     * 修改用户
     * @param u
     * @return
     */
    public int updateUser(User u);

    /**
     * 根据id查询单个用户
     * @param id
     * @return
     */
    public User findById(int id);

}
