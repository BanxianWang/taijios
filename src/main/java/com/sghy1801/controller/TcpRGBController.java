package com.sghy1801.controller;

import com.sghy1801.util.LedStr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.Socket;


import java.io.OutputStream;

/**
 * @author wrm
 * @create 2019-05-16 15:33
 */
@Controller
@RequestMapping("tcptcp")
public class TcpRGBController {
    @RequestMapping("rgb")
    public void TcpRgb(String rgb){
        LedStr.getLedStr().setStr(rgb);
    }

}
