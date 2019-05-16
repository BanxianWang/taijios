package com.sghy1801.util;

/**
 * @author wrm
 * @create 2019-05-16 16:18
 */
public class Test {
    public static void main(String[] args) {
     LedStr.getLedStr().setStr("255,0,0");
        System.out.println(  LedStr.getLedStr().getStr());
    }
}
