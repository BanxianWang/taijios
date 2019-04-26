package com.sghy1801.service.impl;

import com.sghy1801.dao.TemperatureMapper;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired
    private TemperatureMapper temperatureMapper;

    @Override
    public Map<String,Object> getAllTemperature(Map<String, Object> map) {
        Map<String,Object> temperatures = temperatureMapper.getAllTemperature(map);
//        double sum = 0;
//        int count = 0;

//        System.out.println(sum);
//        System.out.println(sum/count);

        return temperatureMapper.getAllTemperature(map);
    }

    @Override
    public int addTemperature(Temperature temperature) {
        return temperatureMapper.addTemperature(temperature);
    }

    @Override
    public int delTemperatureByTime(Map<String, Object> map) {
        return temperatureMapper.delTemperatureByTime(map);
    }
}
