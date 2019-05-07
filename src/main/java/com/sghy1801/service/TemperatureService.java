package com.sghy1801.service;

import com.sghy1801.entity.Temperature;

import java.util.List;
import java.util.Map;

public interface TemperatureService {
    /**
     * 获取逐小时温度信息
     * @param machineID
     * @return
     */
     List<Map> getHoursTemperature(int machineID);

    /**
     * 获取逐天温度信息 （根据map中的条件查询）
     * @param machineID
     * @return
     */
    List<Map> getDaysTemperature(int machineID);

    /**
     * 新增温度信息
     * @param temperature
     * @return
     */
    int addTemperature(Temperature temperature);

    /**
     * 根据时间区间删除温度信息
     * @param map
     * @return
     */
    int delTemperatureByTime(Map<String,Object> map);

    /**
     * 获取最新温度
     * @return
     */
    Temperature getLastTemperature(int id);


    /**
     * 获取当日最高，最低，平均温度
     * @return
     */
    List<Map> getSomeInfo(int machineID);
}
