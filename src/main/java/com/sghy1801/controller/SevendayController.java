package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.SevendayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 未来7日温度信息类控制器
 */

@RequestMapping("/jsp")
@Controller
public class SevendayController {

    @Autowired
    private SevendayService service;

    //获取未来7日温度信息
    @RequestMapping(value = "/getsevenday",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSevenDay( HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Sevenday> list = service.getSevenDay();
        String js = JSONObject.toJSONString(list);
        return "successCallback6("+ js + ")";
    }
}
