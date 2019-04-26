package com.sghy1801.service;

import com.sghy1801.entity.Temperature;

import java.util.List;
import java.util.Map;

public interface TemperatureService {
    /**
     * 获取温度信息 （根据map中的条件查询）
     * @param map
     * @return
     */
    Map<String,Object> getAllTemperature(Map<String,Object> map);

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
}
