package com.sghy1801.entity.meituan;

public class Shop {
    private int id;
    private String sName;//商店名称
    private String saddress;//商店地址
    private double avgprice;//人均消费
    private int pcount;//评论人数
    private double avgcomment;//人均评分

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(double avgprice) {
        this.avgprice = avgprice;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public double getAvgcomment() {
        return avgcomment;
    }

    public void setAvgcomment(double avgcomment) {
        this.avgcomment = avgcomment;
    }

    public Shop(int id, String sName, String saddress, double avgprice, int pcount, double avgcomment) {
        this.id = id;
        this.sName = sName;
        this.saddress = saddress;
        this.avgprice = avgprice;
        this.pcount = pcount;
        this.avgcomment = avgcomment;
    }
}
