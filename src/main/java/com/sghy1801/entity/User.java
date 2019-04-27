package com.sghy1801.entity;

public class User {
    //用户id
    private int id;
    //用户名
    private String username;
    //用户密码
    private String password;
    //用户性别
    private String gender;
    //用户手机号
    private String phone;
    //用户邮箱
    private String email;
    //用户地址
    private String address;
    // 0管理员1普通用户
    private int userorman;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserorman() {
        return userorman;
    }

    public void setUserorman(int userorman) {
        this.userorman = userorman;
    }
}
