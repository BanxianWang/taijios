package com.sghy1801.entity;

public class City {
    //市id
    private int cid;
    //市名称
    private String city;
    //省id
    private Provincial Provincial;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public com.sghy1801.entity.Provincial getProvincial() {
        return Provincial;
    }

    public void setProvincial(com.sghy1801.entity.Provincial provincial) {
        Provincial = provincial;
    }
}
