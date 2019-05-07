package com.sghy1801.service;

import com.sghy1801.entity.Sevenday;

import java.util.List;

public interface SevendayService {

    /**
     * 获取预测的七天天气
     * @return
     */
    public List<Sevenday> getSevenDay();
}
