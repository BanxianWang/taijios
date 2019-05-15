package com.sghy1801.controller;

import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sghy1801.util.JedisUtil;
import com.sghy1801.util.TcpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
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
    public void Tcp(){
        test();
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
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println(123123);
            this.test();
        }


        System.out.println("a client is connected: " + socket.getRemoteSocketAddress());


        //读取客户端数据
        InputStream input = null;
        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] buff = new byte[1024];
        String str = "";
        while (true) {
            int len = 0;
            try {
                len = input.read(buff);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (len > 0) {
                str = new String(buff, 0, len);
                //接到的温度
                double temperature = Double.valueOf(str);
                //放入缓存
                JedisUtil.setTemperature(temperature + "");
                //放入数据库
                Temperature temperature1 = new Temperature();
                temperature1.setTemperature(temperature);
                temperature1.setMachineid(1);
                if (service != null){
                    service.addTemperature(temperature1);
                }
            }
        }
    }
}
