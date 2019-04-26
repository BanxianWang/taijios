package com.sghy1801.controller;

import com.alibaba.fastjson.JSON;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sghy1801.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/jsp")
public class TemperatrueController {

    @Autowired
    private TemperatureService service;

    @RequestMapping(value = "/getAllTemperature",method = RequestMethod.POST)
    public @ResponseBody
    String getAllTemperature(String machineID,String oldtime,String newtime,String word) throws Exception {
        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = sdf.parse(oldtime);
//        Date date2 = sdf.parse(newtime);
        //将获取的值放入map中
        Map<String,Object> map = new HashMap<String,Object>();
//
        if (machineID=="") map.put("machineID",null);
        else  map.put("machineID",machineID);

        if (word=="1"){
                String a = AllUtil.dateMinusMonth(newtime, Integer.parseInt(word));

            System.out.println(newtime+"   "+a);
        }

        if (oldtime=="") map.put("oldtime",null);
        else  map.put("oldtime",oldtime);

        if (newtime=="") map.put("newtime",date);
        else  map.put("newtime",newtime);

        //获取温度列表
        Map<String,Object> temperatures = service.getAllTemperature(map);

        //转换成json格式
        String j = JSON.toJSONString(temperatures);

        return j;
    }






}
