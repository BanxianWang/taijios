package com.sghy1801.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class ToolController {

    @RequestMapping("sevenday")
    @ResponseBody
    public String get(){
        Process res;//此类用于创建操作系统进程
        try {

            //数组第2位开始放入参数 0，1，》2，3.。。。。
            String[] pyargs = new String[] { "python", "F:/paTest1.py","南京"};

            Process proc = Runtime.getRuntime().exec(pyargs);// 执行python脚本文件
            //通过输入流 获取python的返回值
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {//读取python的返回值
                System.out.println(line);
            }

            in.close();//关闭
            proc.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "true";
    }

}
