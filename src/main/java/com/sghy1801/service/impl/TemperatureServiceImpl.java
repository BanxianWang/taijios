package com.sghy1801.service.impl;

import com.sghy1801.dao.TemperatureMapper;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired
    private TemperatureMapper temperatureMapper;

    @Override
    public List<Map> getHoursTemperature(Map<String, Object> map) {
        return temperatureMapper.getHoursTemperature(map);
    }

    @Override
    public List<Map> getDaysTemperature(Map<String, Object> map) {
        return temperatureMapper.getDaysTemperature(map);
    }

    @Override
    public int addTemperature(Temperature temperature) {
        return temperatureMapper.addTemperature(temperature);
    }

    @Override
    public int delTemperatureByTime(Map<String, Object> map) {
        return temperatureMapper.delTemperatureByTime(map);
    }

    @Override
    public Temperature getLastTemperature(int machineID) {
        return temperatureMapper.getLastTemperature(machineID);
    }
}
