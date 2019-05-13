package com.sghy1801.service;

import com.sghy1801.entity.News;

import java.util.List;

public interface NewsService {

    /**
     * 获取所有新闻
     * @return
     */
    public List<News> getAllNews();
}
