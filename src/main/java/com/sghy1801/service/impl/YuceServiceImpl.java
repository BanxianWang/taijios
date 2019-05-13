package com.sghy1801.service.impl;

import com.sghy1801.dao.YuceMapper;
import com.sghy1801.entity.Yuce;
import com.sghy1801.service.YuceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YuceServiceImpl implements YuceService {

    @Autowired
    public YuceMapper mapper;

    @Override
    public List<Yuce> getAllYuce() {
        return mapper.getAllYuce();
    }
}
