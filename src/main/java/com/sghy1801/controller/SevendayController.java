package com.sghy1801.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.SevendayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

@RequestMapping("/jsp")
@Controller
public class SevendayController {

    @Autowired
    private SevendayService service;

    @RequestMapping(value = "/getsevenday",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSevenDay(){
        List<Sevenday> list = service.getSevenDay();
        return JSONObject.toJSONString(list);
    }
}
