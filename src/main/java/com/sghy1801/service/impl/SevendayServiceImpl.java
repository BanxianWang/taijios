package com.sghy1801.service.impl;

import com.sghy1801.dao.SevendayMapper;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.SevendayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SevendayServiceImpl implements SevendayService {

    @Autowired
    private SevendayMapper mapper;

    @Override
    public List<Sevenday> getSevenDay() {
        return mapper.getSevenDay();
    }
}
