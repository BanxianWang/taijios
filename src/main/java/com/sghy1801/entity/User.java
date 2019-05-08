package com.sghy1801.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    //用户id
    private int id;
    //身份证号
    private String IDnumber;
    //用户名
    private String username;
    //用户密码
    private String password;
    //注册日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    //用户地址
    private String address;
    //用户邮箱
    private String email;
    //用户手机号
    private String phone;
    //用户性别0男1女
    private int gender;
    // 0管理员1普通用户
    private int userorman;
    //用户状态0已启用1禁用
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUserorman() {
        return userorman;
    }

    public void setUserorman(int userorman) {
        this.userorman = userorman;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
