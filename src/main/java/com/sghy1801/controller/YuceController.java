package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Yuce;
import com.sghy1801.service.YuceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/jsp")
@Controller
public class YuceController {

    @Autowired
    private YuceService service;


    /**
     * 获得预测的各个指数信息
     * @param response
     * @return
     */
    @RequestMapping(value = "/getAllYuce",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getAllYuce(HttpServletResponse response){
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //获得预测的各个指数信息
        List<Yuce> list = service.getAllYuce();
        //return "successCallback1(" + JSONObject.toJSONString(list) + ")";
        return  JSONObject.toJSONString(list);
    }
}
