package com.sghy1801.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping("/jsp")
public class TemperatrueController {

    @Autowired
    private TemperatureService service;

    /**
     * 逐小时的温度
     * @param machineID
     * @param oldtime
     * @param newtime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getHoursTemperature",method = RequestMethod.POST)
    public @ResponseBody
    String getHoursTemperature(String machineID,String oldtime,String newtime){

        //将获取的值放入map中
        Map<String,Object> map = new HashMap<String,Object>();

        if (machineID=="") map.put("machineID",null);
        else  map.put("machineID",machineID);

        if (oldtime=="") map.put("oldtime",null);
        else  map.put("oldtime",oldtime);

        if (newtime=="") map.put("newtime",null);
        else  map.put("newtime",newtime);

        //获取温度列表
        List<Map> temperatures = service.getHoursTemperature(map);
        String json = "";
        JSONObject jsonObject = new JSONObject();
        int count = temperatures.size();
        int num = 0;
        Object[] arr = new Object[24];
        for (Map temperature : temperatures) {
            int hours = Integer.parseInt(temperature.get("hours").toString().substring(8,10));
            arr[hours-1] = temperature.get("hoursavg");
        }
        jsonObject.put("hoursavg",arr);

        return ""+jsonObject;
    }

    /**
     * 逐天的温度
     * @param machineID
     * @param oldtime
     * @param newtime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDaysTemperature",method = RequestMethod.POST)
    public @ResponseBody
    String getDaysTemperature(String machineID,String oldtime,String newtime){
        Date date = new Date();

        //将获取的值放入map中
        Map<String,Object> map = new HashMap<String,Object>();

        if (machineID=="") map.put("machineID",null);
        else  map.put("machineID",machineID);

        if (oldtime=="") map.put("oldtime",null);
        else  map.put("oldtime",oldtime);

        if (newtime=="") map.put("newtime",null);
        else  map.put("newtime",newtime);

        //获取温度列表
        List<Map> temperatures = service.getDaysTemperature(map);

        //转换成json格式
        String j = JSON.toJSONString(temperatures);
        System.out.println(j);
        return j;
    }


    //获取最新温度
    @RequestMapping(value = "/getLastTemperature")
    @ResponseBody
    public String getLastTemperature(@RequestParam(value = "machineID",defaultValue = "1") String machineID,
                                     HttpServletResponse response
                                     ){
        response.setHeader("Access-Control-Allow-Origin","*");
        Temperature temperature = service.getLastTemperature(Integer.parseInt(machineID));
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("temperature",temperature);
        String j = JSONObject.toJSONString(map);
        System.out.println(j);
        return "successCallback("+j+")";

    }







}
