package com.sghy1801.service.impl;

import com.sghy1801.dao.TemperatureMapper;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired
    private TemperatureMapper temperatureMapper;

    //获取逐小时温度
    @Override
    public List<Map> getHoursTemperature(int machineID) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        map.put("machineID", machineID);
        //格式化日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String newtime = sdf.format(date);
        String oldtiem = sdf1.format(date);
        //获取时间段
        map.put("oldtime", oldtiem + " 00:00:00");
        map.put("newtime", newtime);

        return temperatureMapper.getHoursTemperature(map);
    }

    //获取一天的平均温度
    @Override
    public List<Map> getDaysTemperature(int machineID) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //当前日期
        String newtime = sdf.format(date);
        int time = date.getDay();
        if (time==0)time=7;
        //当前日期前七天
        calendar.add(Calendar.DATE, -time+1);
        String oldtime = sdf.format(calendar.getTime());
        System.out.println(oldtime);
        //获取所有需要的数据
        map.put("machineID", machineID);
        map.put("oldtime",oldtime+" 00:00:00");
        map.put("newtime",newtime+" 00:00:00");
        return temperatureMapper.getDaysTemperature(map);
    }

    //新增温度
    @Override
    public int addTemperature(Temperature temperature) {
        return temperatureMapper.addTemperature(temperature);
    }

    //根据时间删除温度
    @Override
    public int delTemperatureByTime(Map<String, Object> map) {
        return temperatureMapper.delTemperatureByTime(map);
    }

    //获取最新温度
    @Override
    public Temperature getLastTemperature(int machineID) {
        return temperatureMapper.getLastTemperature(machineID);
    }

    //获取最高最低平均温度
    @Override
    public Map getSomeInfo(int machineID) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        map.put("machineID", machineID);
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String newtime = sdf.format(date);
        String oldtiem = sdf1.format(date);
        //获取时间段
        map.put("oldtime", oldtiem + " 00:00:00");
        map.put("newtime", newtime);
        return temperatureMapper.getSomeInfo(map);
    }
}
