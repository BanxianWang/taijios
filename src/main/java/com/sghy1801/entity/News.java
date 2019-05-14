package com.sghy1801.entity;

public class News {
    private int id;
    private String title;     //标题
    private String context;  //内容
    private String type;     //类型
    private String anthor;  //作者
    private String url;     //路径
    private String mdftime;//时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnthor() {
        return anthor;
    }

    public void setAnthor(String anthor) {
        this.anthor = anthor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMdftime() {
        return mdftime;
    }

    public void setMdftime(String mdftime) {
        this.mdftime = mdftime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


}
