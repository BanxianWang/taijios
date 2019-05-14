package com.sghy1801.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wrm
 * @create 2019-05-14 17:02
 */
public class TcpUtil {

    public static void test(){
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
            TcpUtil.test();
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
        while(true)
        {
            int len = 0;
            try {
                len = input.read(buff);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(len > 0)
            {
                str=new String(buff, 0, len);
                double temperature = Double.valueOf(str);
                JedisUtil.setTemperature(temperature+"");
                System.out.println(str);
            }
        }
    }
}
