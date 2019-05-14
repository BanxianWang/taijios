package com.sghy1801.controller;

import com.sghy1801.util.TcpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wrm
 * @create 2019-05-14 18:38
 */
@Controller
@RequestMapping("tcptcp")
public class TcpController {
    @RequestMapping("tcp")
    public void Tcp(){
       new TcpUtil().test();
    }
}
