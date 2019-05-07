package com.sghy1801.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Sevenday {
    private int id;
    @JSONField(format = "utf-8")
    private String date;  //时间
    @JSONField(format = "utf-8")
    private String wd;  //温度
    private String fl;  //风力
    private String tq;  //天气

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getTq() {
        return tq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }
}
