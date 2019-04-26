package com.sghy1801.entity;

import java.util.Date;

public class Temperature {
    private int id;               //id
    private Date temtime;            //时间
    private int machineid; //机器id（外键）
    private double temperature; //温度

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTemtime() {
        return temtime;
    }

    public void setTemtime(Date temtime) {
        this.temtime = temtime;
    }

    public int getMachineid() {
        return machineid;
    }

    public void setMachineid(int machineid) {
        this.machineid = machineid;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
