package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.News;
import com.sghy1801.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService service;


    @RequestMapping("getNews")
    public String getAllNews(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<News> list = service.getAllNews();

        return "successCallback(" + JSONObject.toJSONString(list) + ")";
    }
}



