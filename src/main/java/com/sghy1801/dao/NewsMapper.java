package com.sghy1801.dao;

import com.sghy1801.entity.News;

import java.util.List;

public interface NewsMapper {

    /**
     * 获取所有新闻
     * @return
     */
    public List<News> getAllNews();
}
