package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.News;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.impl.NewsServiceImpl;
import com.sghy1801.service.impl.SevendayServiceImpl;
import com.sghy1801.service.impl.TemperatureServiceImpl;
import com.sghy1801.util.AA;
import com.sghy1801.util.JedisUtil;
import com.sghy1801.util.TTS;
import org.apache.log4j.FileAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class ToolController {


    @Autowired
    TemperatureServiceImpl TemperatureServiceImpl;//获取最新温度 getLastTemperature()
    @Autowired
    SevendayServiceImpl SevendayServiceImpl;//获取七天的天气集合 getSevenDay()
    @Autowired
    NewsServiceImpl NewsServiceImpl;//获取新闻集合 getAllNews()


    @RequestMapping(value = "getTTS", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getTTS(String str, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        String filePath = request.getServletContext().getRealPath(File.separator);
        filePath += File.separator + "statics" + File.separator + "voice";
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        AA.setPath(filePath);

        System.out.println("问题是：=" + str);
        String ans = getans(str);//机器回答
        System.out.println("回答是：=" + ans);
        File filedelete = new File(AA.getPath() + File.separator + "tts" + AA.getOut() + ".wav");
        if (filedelete.exists()) {
            filedelete.delete();
        }
        String filename = "";
        String json = "";
        Map<String, Object> param = new HashMap<>();

        try {
            TTS a = new TTS("X9PvsnlQsXI1oPkj");
            a.process(ans);
            filename = AA.getOut();
            param.put("result", "http://192.168.1.13:8080/statics/voice/tts" + filename + ".wav");
            json = JSONObject.toJSONString(param);
            System.out.println(json);
            return "successCallback5(" + json + ")";
        } catch (Exception e) {
        }
        return "successCallback5(" + json + ")";
    }


    public String getans(String str) {

        String ans = "";
        if (Pattern.compile("温度").matcher(str).find()) {//Str字符串中出现字符串“温度”五个字符中的任何一个，那么则返回true
           // ans = "当前实时温度是" + TemperatureServiceImpl.getLastTemperature(1).getTemperature() + "摄氏度";
            ans = "当前实时温度是" + JedisUtil.getTemperature() + "摄氏度";
        } else if (Pattern.compile("天气").matcher(str).find()) {
            List<Sevenday> sevendayList = SevendayServiceImpl.getSevenDay();
            ans = "今天的天气是" + sevendayList.get(0).getTq() + "";
        } else if (Pattern.compile("天气预报").matcher(str).find()) {
            List<Sevenday> sevendayList = SevendayServiceImpl.getSevenDay();
            ans = "明天" + sevendayList.get(1).getDate() + "天气" + sevendayList.get(1).getTq() + "，温度" + sevendayList.get(1).getWd() + ",风力" + sevendayList.get(1).getFl() + "";
        } else if (Pattern.compile("后天").matcher(str).find()) {
            List<Sevenday> sevendayList = SevendayServiceImpl.getSevenDay();
            ans = "后天" + sevendayList.get(2).getDate() + "天气" + sevendayList.get(2).getTq() + "，温度" + sevendayList.get(2).getWd() + ",风力" + sevendayList.get(2).getFl() + "";
        } else if (Pattern.compile("体育").matcher(str).find()) {
            List<News> news = NewsServiceImpl.getAllNews();
            ans = "来自" + news.get(8).getAnthor() + "的" + news.get(8).getType() + "新闻：" + news.get(8).getContext() + "";
            System.out.println(news);
        } else if (Pattern.compile("娱乐").matcher(str).find()) {
            List<News> news = NewsServiceImpl.getAllNews();
            ans = "来自" + news.get(12).getAnthor() + "的" + news.get(12).getType() + "新闻：" + news.get(12).getContext() + "";
        } else if (Pattern.compile("新闻").matcher(str).find()) {
            List<News> news = NewsServiceImpl.getAllNews();
            ans = "来自" + news.get(0).getAnthor() + "的" + news.get(0).getType() + "新闻：" + news.get(0).getContext() + "";
            System.out.println(news.get(0).getContext());
        } else {
            ans = "不知道你说的是什么？再试一次吧！";
        }
        return ans;
    }

}
