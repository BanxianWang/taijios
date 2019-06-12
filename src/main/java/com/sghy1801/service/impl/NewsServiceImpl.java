package com.sghy1801.service.impl;

import com.sghy1801.dao.NewsMapper;
import com.sghy1801.entity.News;
import com.sghy1801.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper mapper;

    //获取所有新闻
    @Override
    public List<News> getAllNews() {
        return mapper.getAllNews();
    }
}
