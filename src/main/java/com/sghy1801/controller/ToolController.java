package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.News;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.entity.meituan.Shop;
import com.sghy1801.service.impl.NewsServiceImpl;
import com.sghy1801.service.impl.SevendayServiceImpl;
import com.sghy1801.service.impl.ShopServiceImpl;
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
    ShopServiceImpl shopService;//获取商店列表
    @Autowired
    AnsUtil ansUtil;

    String type="";//y，s，z，x，q 衣,食,住,行，其他

    /**
     * 普通的语音接口
     * */
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


    /**
     * 获取商店的语音接口 比普通的多返回一个商店列表 param.put("shops",shops)
     * */
    @RequestMapping(value = "getshopTTS", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getshopTTS(String str, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
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
            List<Shop> shops = shopService.getAlllist();
            param.put("shops",shops);//把获取到的商店列表返回出去
            json = JSONObject.toJSONString(param);
            return "successCallback5(" + json + ")";
        } catch (Exception e) {

        }
        return "successCallback5(" + json + ")";
    }

    /**
     * 获取回答
     * */
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
        if(str.indexOf("找") >= 0||str.indexOf("附近") >= 0||str.indexOf("给我") >= 0||str.indexOf("来一个") >= 0||str.indexOf("饭") >= 0||str.indexOf("吃") >= 0||str.indexOf("雨花台") >= 0||str.indexOf("小行") >= 0||str.indexOf("买") >= 0||str.indexOf("帮我") >= 0||str.indexOf("店") >= 0){
            String sortby="";
            if(str.indexOf("价格") >= 0||str.indexOf("高到低") >= 0){
                sortby="价格高到低";
            }else  if(str.indexOf("价格") >= 0||str.indexOf("低到高") >= 0){
                sortby="价格低到高";
            }else  if(str.indexOf("人气") >= 0||str.indexOf("排") >= 0){
                sortby="人气";
            }else  if(str.indexOf("评价") >= 0||str.indexOf("排") >= 0){
                sortby="评价";
            }

            if(str.indexOf("雨花") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("雨花台区","网吧",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("雨花台区","美食",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("雨花台区","喝",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("雨花台区","奶茶",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("雨花台区","饭",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("雨花台区","随便",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }
            }else if(str.indexOf("新街") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("新街口","网吧",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("新街口","美食",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("新街口","喝",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("雨花台区","奶茶",sortby);
                    ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("新街口","饭",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("新街口","随便",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }
            }else if(str.indexOf("小行") >= 0||str.indexOf("安德门") >= 0||str.indexOf("安") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("安德门小行","网吧",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("安德门小行","美食",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("安德门小行","喝",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("安德门小行","奶茶",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("安德门小行","饭",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("安德门小行","随便",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }
            }else if(str.indexOf("夫子庙") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("夫子庙","网吧",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("夫子庙","美食",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("夫子庙","喝",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("夫子庙","奶茶",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("夫子庙","饭",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("夫子庙","随便",sortby);
                }
            }else if(str.indexOf("虹悦城") >= 0||str.indexOf("城") >= 0||str.indexOf("红") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("虹悦城","网吧",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("虹悦城","美食",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("虹悦城","喝",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("虹悦城","奶茶",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("虹悦城","饭",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("虹悦城","随便",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }
            }else if(str.indexOf("大学城") >= 0||str.indexOf("江宁") >= 0){
                if(str.indexOf("网") >= 0){
                    dopython("江宁大学城","网吧",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("吃") >= 0){
                    dopython("江宁大学城","美食",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("喝") >= 0){
                    dopython("江宁大学城","喝",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("奶茶") >= 0){
                    dopython("江宁大学城","奶茶",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else if (str.indexOf("饭") >= 0){
                    dopython("江宁大学城","饭",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }else {
                    dopython("江宁大学城","随便",sortby);ansUtil.putmapsdian();//获取店铺
                    ans= ansUtil .getanswer("评分高");
                }
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

    /**
     *执行脚本文件
     * */
    public void dopython(String address,String sp,String sortby){
        if (address.equals("安德门小行")){
            address="14858";
        }else if(address.equals("新街口")){
            address="831";
        }else if(address.equals("雨花台区")){
            address="834";
        }else if(address.equals("夫子庙")){
            address="832";
        }else if(address.equals("虹悦城")){
            address="9308";
        }else if(address.equals("江宁大学城")){
            address="9463";
        }
        String pyurl="";
        if(sortby.equals("价格低到高")){// 价格低到高 价格高到低 人气 评价 default智能
            pyurl="https://apimobile.meituan.com/group/v4/poi/pcsearch/55?uuid=c4cfaa0b9406493b8f80.1558359069.1.0.0&userid=-1&limit=32&offset=0&cateId=-1&q="+sp+"&sort=price&areaId="+address+"";;
        }else if(sortby.equals("价格高到低")){//priceDesc高到低
            pyurl="https://apimobile.meituan.com/group/v4/poi/pcsearch/55?uuid=c4cfaa0b9406493b8f80.1558359069.1.0.0&userid=-1&limit=32&offset=0&cateId=-1&q="+sp+"&sort=priceDesc&areaId="+address+"";;
        }else if(sortby.equals("人气")){//solds人气
            pyurl="https://apimobile.meituan.com/group/v4/poi/pcsearch/55?uuid=c4cfaa0b9406493b8f80.1558359069.1.0.0&userid=-1&limit=32&offset=0&cateId=-1&q="+sp+"&sort=solds&areaId="+address+"";;
        }else if(sortby.equals("评价")){//rating评价
            pyurl="https://apimobile.meituan.com/group/v4/poi/pcsearch/55?uuid=c4cfaa0b9406493b8f80.1558359069.1.0.0&userid=-1&limit=32&offset=0&cateId=-1&q="+sp+"&sort=rating&areaId="+address+"";;
        }else {//default智能
            pyurl="https://apimobile.meituan.com/group/v4/poi/pcsearch/55?uuid=c4cfaa0b9406493b8f80.1558359069.1.0.0&userid=-1&limit=32&offset=0&cateId=-1&q="+sp+"&sort=default&areaId="+address+"";;
        }

        Process res;//此类用于创建操作系统进程
        try {
            //数组第2位开始放入参数 0，1，》2，3.。。。。c
            String[] pyargs = new String[] { "python", "/opt/food.py",pyurl};
            Process proc = Runtime.getRuntime().exec(pyargs);// 执行python脚本文件
            //通过输入流 获取python的返回值
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {//读取python的返回值
                System.out.println(line);
            }
            in.close();//关闭
            proc.waitFor();//调用Process.waitfor等待子进程完成
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
