package com.sghy1801.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wrm
 * @create 2019-05-16 16:14
 */
public class LedStr {
    private static LedStr  ledStr = new LedStr();
    private String str = "0,0,0,500";

    private LedStr() {
    }

    public static LedStr getLedStr() {
        return ledStr;
    }


    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
