package com.sghy1801.service;

import com.sghy1801.entity.User;


import java.util.List;
import java.util.Map;


public interface UserService {
    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    public User login(String phone, String password);

    /**
     * 查询用户列表页
     * @param currentPage
     * @return
     */
    public List<User> listUser(int currentPage, String username, String phone);

    /**
     * 查询用户记录数
     * @return
     */
    public int countUser(String username, String phone);

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


    public List<Map> getDate();


    /**
     * 修改状态
     * @param userId
     * @param changestate
     * @return
     */
    public int updateChanges(int userId, int changestate);


    /**
     * 修改密码
     * @param id
     * @param repwd
     * @return
     */
   public int updatePass(int id, String repwd, String username, String email, String address, String phone);





    /**
     * 拥有设备人数最多的前5个省份
     * @return
     */
    public List<Map> getLocalDistribution();


    public int getNowYearcount();

}
