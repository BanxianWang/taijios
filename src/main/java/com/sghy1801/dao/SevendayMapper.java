package com.sghy1801.dao;

import com.sghy1801.entity.Sevenday;

import java.util.List;

public interface SevendayMapper {

    /**
     * 获取预测的七天天气
     * @return
     */
    public List<Sevenday> getSevenDay();
}
