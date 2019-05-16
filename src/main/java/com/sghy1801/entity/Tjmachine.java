package com.sghy1801.entity;

public class Tjmachine {
    private int id;     //机器ID
    private String city; //机器所在城市
    private int temperature;//是否有温度控件 1有0无 默认为0
    private int  usetime;//使用时间
    private int runningstate;//运行状态
    private User userid;  //用户外键

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getRunningstate() {
        return runningstate;
    }

    public void setRunningstate(int runningstate) {
        this.runningstate = runningstate;
    }

    public int getUsetime() {
        return usetime;
    }

    public void setUsetime(int usetime) {
        this.usetime = usetime;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }
}
