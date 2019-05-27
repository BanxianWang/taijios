package com.sghy1801.entity.meituan;

public class Shop {
    private int id;
    private String sName;//商店名称
    private String saddress;//商店地址
    private double avgprice;//人均消费
    private int pcount;//评论人数
    private double avgcomment;//人均评分

    private double latitude;//商店的地图坐标
    private double longitude;
    private String imgurl;//商店的图标

    private  String shoptype;

    public String getShoptype() {
        return shoptype;
    }

    public void setShoptype(String shoptype) {
        this.shoptype = shoptype;
    }

    public Shop(int id, String sName, String saddress, double avgprice, int pcount, double avgcomment, double latitude, double longitude, String imgurl, String shoptype) {
        this.id = id;
        this.sName = sName;
        this.saddress = saddress;
        this.avgprice = avgprice;
        this.pcount = pcount;
        this.avgcomment = avgcomment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgurl = imgurl;
        this.shoptype = shoptype;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Shop(int id, String sName, String saddress, double avgprice, int pcount, double avgcomment, double latitude, double longitude, String imgurl) {
        this.id = id;
        this.sName = sName;
        this.saddress = saddress;
        this.avgprice = avgprice;
        this.pcount = pcount;
        this.avgcomment = avgcomment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgurl = imgurl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

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



}
