package com.sghy1801.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sghy1801.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;


@Controller
@RequestMapping("/jsp")
public class TemperatrueController {

    @Autowired
    private TemperatureService service;

    /**
     * 逐小时的温度
     *
     * @param machineID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getHoursTemperature")
    public @ResponseBody
    String getHoursTemperature(String machineID, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Date date = new Date();
        System.out.println(11111111);
        //将获取的值放入map中
        Map<String, Object> map = new HashMap<String, Object>();

        if (machineID == "") map.put("machineID", null);
        else map.put("machineID", machineID);


        //获取温度列表
        List<Map> temperatures = service.getHoursTemperature(Integer.parseInt(machineID));
        String json = "";
        JSONObject jsonObject = new JSONObject();
        int count = temperatures.size();
        Object[] arr = new Object[24];

        for (Map temperature : temperatures) {

            int hours = Integer.parseInt(temperature.get("hours").toString().substring(11, 13));
            DecimalFormat df = new DecimalFormat("0.00");
            arr[hours] = temperature.get("hoursavg");
        }
        jsonObject.put("hoursavg", arr);
        return "successCallback1(" + jsonObject.toJSONString() + ")";

    }

    /**
     * 逐天的温度
     *
     * @param machineID
     * @throws Exception
     * @return3
     */
    @RequestMapping(value = "/getDaysTemperature")
    public @ResponseBody
    String getDaysTemperature(Integer machineID, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //获取温度列表
        List<Map> temperatures = service.getDaysTemperature(1);
        Object[] arr = new Object[7];
        int i = 0;
        for (Map temperature : temperatures) {

            int days = Integer.parseInt(temperature.get("days").toString().substring(8, 10));
            DecimalFormat df = new DecimalFormat("0.00");
            arr[i] = df.format(temperature.get("daysavg"));
            i++;
        }
        //转换成json格式
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("daysavg", arr);


        return "successCallback2(" + jsonObject.toJSONString() + ")";
    }


    //获取最新温度
    @RequestMapping(value = "/getLastTemperature")
    @ResponseBody
    public String getLastTemperature(@RequestParam(value = "machineID", defaultValue = "1") String machineID,
                                     HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> map = new HashMap<String, Object>();
        String temperatureStr = JedisUtil.getTemperature();

        if (temperatureStr == null || temperatureStr.equals("")) {
            Temperature temperature = service.getLastTemperature(Integer.parseInt(machineID));
            map.put("temperature", temperature);
        } else {
            Temperature temperature = new Temperature();
            temperature.setTemperature(Double.parseDouble(JedisUtil.getTemperature()));
            temperature.setMachineid(1);
            map.put("temperature", temperature);
        }
        String j = JSONObject.toJSONString(map);
        System.out.println(JedisUtil.getTemperature());
        return "successCallback(" + j + ")";
    }


    /**
     * 平均，最高，最低的温度
     *
     * @param machineID
     * @throws Exception
     * @return3
     */
    @RequestMapping(value = "/getSomeInfo")
    @ResponseBody
    public String getSomeInfo(Integer machineID, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        Map temperatures = service.getSomeInfo(machineID);
        DecimalFormat df = new DecimalFormat("0.00");
        temperatures.put("avg",df.format(temperatures.get("avg")));
        //转换成json格式
        String j = JSON.toJSONString(temperatures);
        return "successCallback3(" + j + ")";
    }

    /**
     * 新增
     *
     * @param temperature
     * @return
     */
    public String addTemperature(double temperature) {
        Temperature temperature1 = new Temperature();
        temperature1.setMachineid(1);
        temperature1.setTemperature(temperature);
        int count = service.addTemperature(temperature1);
        if (count == 1) {
            return "true";
        } else {
            return "false";
        }
    }

}
