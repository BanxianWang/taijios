package com.sghy1801.util;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class AA {
    private  static String str;
    private static  String path;
    public AA(String s) {
        this.str = s;
    }

    public AA() {
    }
    public static void setPath(String path1){
        path = path1;
    }
    public static String  getOut(){
        return  str;
    }
    public static String  getPath(){
        return  path;
    }
}
