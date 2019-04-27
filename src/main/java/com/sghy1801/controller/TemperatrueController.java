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
import org.springframework.web.bind.annotation.ResponseBody;


import javax.json.Json;
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
    String getHoursTemperature(String machineID,String oldtime,String newtime) throws Exception {
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
        List<Map> temperatures = service.getHoursTemperature(map);
        JSONObject jsonObject = new JSONObject();
        String day = "";
        //所需数组个数
        int count = temperatures.size()%24==0?temperatures.size()/24:temperatures.size()/24+1;
        for (int i = 0; i < count; i++) {
            if (day==""){
                day = temperatures.get(i).get("hours").toString().substring(8,13);
                System.out.println(day);
            }else {
                if (!day.equals(temperatures.get(i).get("hours").toString().substring(8,13))){

                }
            }
            jsonObject.put("hours",temperatures.get(i).get("hours"));

            Object[] arr = new Object[24];
            int size =temperatures.size();
            for (int j = 0; j <= size ; j++) {
                if (temperatures.get(j).get("hoursavg")==null){
                    arr[j] = null;
                }
                arr[j]= temperatures.get(j).get("hoursavg");
                jsonObject.put("hoursavg",arr);
            }
        }


        //转换成json格式

        String j = JSON.toJSONString(jsonObject);
        System.out.println(j);
        return "";
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
    String getDaysTemperature(String machineID,String oldtime,String newtime) throws Exception {
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

        return j;
    }

    @RequestMapping(value = "/getLastTemperature",method = RequestMethod.POST)
    public @ResponseBody String getLastTemperature(String machineID) throws Exception {
        Temperature temperature = service.getLastTemperature(Integer.parseInt(machineID));

        String j = JSON.toJSONString(temperature);
        System.out.println(j);
        return j;
    }







}
