package com.sghy1801.controller;

import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sghy1801.util.JedisUtil;
import com.sghy1801.util.LedStr;
import com.sghy1801.util.TcpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wrm
 * @create 2019-05-14 18:38
 */
@Controller
@RequestMapping("tcptcp")
public class TcpController {
    @Autowired
    private TemperatureService service;

    @RequestMapping("tcp")
    public void Tcp() {
        try {
            test();
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            test();
        }

    }

    public void test() {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("local ip = " + listener.getLocalSocketAddress());

        Socket socket = null;


        //服务端等待客户端连接，默认超时时间: 60 seconds
        try {
            socket = listener.accept();
            System.out.println(socket.getRemoteSocketAddress());
            System.out.println("a client is connected: " + socket.getRemoteSocketAddress());
        } catch (Exception e) {

        }
        //读取客户端数据
        InputStream input = null;
        OutputStream out = null;
        try {
            input = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buff = new byte[1024];
        String str = "";
        while (true) {
            try {
                int len = 0;

                len = input.read(buff);
                if (len > 0) {
                    str = new String(buff, 0, len);
                    //接到的温度
                    if (str != null) {
                        double temperature = Double.valueOf(str);
                        if (temperature > 0 && temperature < 45) {
                            System.out.println(temperature);
                            JedisUtil.setTemperature(temperature + "");
                            //放入数据库
                            Temperature temperature1 = new Temperature();
                            temperature1.setTemperature(temperature);
                            temperature1.setMachineid(1);
                            if (service != null) {
                                service.addTemperature(temperature1);
                            }
                        }

                    }

                } else {
                    Thread.sleep(1000);
                    test();
                }
                String ledStr = LedStr.getLedStr().getStr();
                //发送数据到客户端
                out.write(ledStr.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
