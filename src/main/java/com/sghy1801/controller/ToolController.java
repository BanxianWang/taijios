package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.News;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.service.impl.NewsServiceImpl;
import com.sghy1801.service.impl.SevendayServiceImpl;
import com.sghy1801.service.impl.TemperatureServiceImpl;
import com.sghy1801.util.*;
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
import java.util.Random;
import java.util.regex.Pattern;

@Controller
public class ToolController {


    @Autowired
    TemperatureServiceImpl TemperatureServiceImpl;//获取最新温度 getLastTemperature()
    @Autowired
    SevendayServiceImpl SevendayServiceImpl;//获取七天的天气集合 getSevenDay()
    @Autowired
    NewsServiceImpl NewsServiceImpl;//获取新闻集合 getAllNews()

    @Autowired
    AnsUtil ansUtil;

    String type="";//y，s，z，x，q 衣,食,住,行，其他

    @RequestMapping(value = "getTTS", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getTTS(String str, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String filePath = request.getServletContext().getRealPath(File.separator);
        filePath += File.separator + "statics" + File.separator + "voice";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        AA.setPath(filePath);

        String ans = getans(str);//机器回答
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
            param.put("result", "http://106.14.208.219:8080/taijios/statics/voice/tts" + filename + ".wav");
            //param.put("result", "http://106.14.208.219:8080/taijios/statics/voice/tts" + filename + ".wav");
            json = JSONObject.toJSONString(param);
            return "successCallback5(" + json + ")";
        } catch (Exception e) {

        }
        return "successCallback5(" + json + ")";
    }


    public String getans(String str) {
        String ans = "";
        //天气类语音
        if (str.indexOf("温度") >= 0||str.indexOf("天气") >= 0){
            ansUtil.putmapwendu();//获取到天气新数据
            ansUtil.putmapseven();//获取到天气预报
            if (str.indexOf("温度") >= 0 ) {
                ans= ansUtil .getanswer("温度");//当前实时温度
            }else if(str.indexOf("天气")>=0){
                ans= ansUtil .getanswer("天气");//今天的天气
            }else if(str.indexOf("预报") >= 0){
                ans= ansUtil .getanswer("预报");//明天的天气
            }else if(str.indexOf("后天") >= 0){
                ans= ansUtil .getanswer("后天");//后天的天气
            }else if(str.indexOf("大后天") >= 0){
                ans= ansUtil .getanswer("大后天");//大后天的天气
            }
        }

        //新闻类语音
        if (str.indexOf("新闻") >= 0){
            ansUtil.putmapxinwen();//获取到新的新闻
            if(str.indexOf("体育") >= 0){
                ans= ansUtil .getanswer("体育");
            }else if(str.indexOf("娱乐") >= 0){
                ans= ansUtil .getanswer("娱乐");
            }else if(str.indexOf("新闻") >= 0){
                ans= ansUtil .getanswer("新闻");
            }
        }
        if (str.indexOf("新闻") >= 0 && str.indexOf("随便") >= 0){//随机新闻类
            ansUtil.putmapxinwensj();//获取到新的新闻
            Random r3 = new Random();
            int r = Math.abs(r3.nextInt() % 10);
            ans= ansUtil .getanswer("随机"+r);
        }


        //美团类语音
        if(str.indexOf("来一个") >= 0||str.indexOf("饭") >= 0||str.indexOf("吃") >= 0){
            ansUtil.putmapsdian();//重新获取商店
            if(str.indexOf("距离") >= 0||str.indexOf("近") >= 0){
                ans= ansUtil .getanswer("距离近");
            }else if(str.indexOf("距离") >= 0||str.indexOf("远") >= 0){
                ans= ansUtil .getanswer("距离远");
            }else if(str.indexOf("消费") >= 0||str.indexOf("高") >= 0){
                ans= ansUtil .getanswer("消费高");
            }else if(str.indexOf("消费") >= 0||str.indexOf("低") >= 0){
                ans= ansUtil .getanswer("消费低");
            }else if(str.indexOf("评分") >= 0||str.indexOf("高") >= 0){
                ans= ansUtil .getanswer("评分高");
            }else if(str.indexOf("评分") >= 0||str.indexOf("低") >= 0){
                ans= ansUtil .getanswer("评分低");
            }
        }
        if(str.indexOf("灯") >= 0){
            if(str.indexOf("蓝") >= 0){
                    LedStr.getLedStr().setStr("0,0,255,1000");
                    ans= "好的，没问题！蓝色";
            }else if(str.indexOf("红") >= 0){
                LedStr.getLedStr().setStr("255,0,0,1000");
                ans= "好的，没问题！调成红色了";
            }else if(str.indexOf("绿") >= 0){
                LedStr.getLedStr().setStr("0,255,0,1000");
                ans= "好的，没问题！";
            }else if(str.indexOf("粉") >= 0){
                LedStr.getLedStr().setStr("237,17,185,1000");
                ans= "好的，没问题！粉色";
            }else if(str.indexOf("黄") >= 0){
                LedStr.getLedStr().setStr("255,255,0,1000");
                ans= "好的，没问题！yellow";
            }
        }
        return ans;
    }

}
