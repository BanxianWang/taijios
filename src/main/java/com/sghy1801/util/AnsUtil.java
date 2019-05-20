package com.sghy1801.util;

import com.sghy1801.entity.News;
import com.sghy1801.entity.Sevenday;
import com.sghy1801.entity.Temperature;
import com.sghy1801.entity.meituan.Shop;
import com.sghy1801.service.impl.NewsServiceImpl;
import com.sghy1801.service.impl.SevendayServiceImpl;
import com.sghy1801.service.impl.ShopServiceImpl;
import com.sghy1801.service.impl.TemperatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AnsUtil {

    @Autowired
    TemperatureServiceImpl TemperatureServiceImpl;//获取最新温度 getLastTemperature()
    @Autowired
    SevendayServiceImpl SevendayServiceImpl;//获取七天的天气集合 getSevenDay()
    @Autowired
    NewsServiceImpl NewsServiceImpl;//获取新闻集合 getAllNews()
    @Autowired
    ShopServiceImpl shopService;//获取商店列表
    static HashMap<String,String> answer;
    static {
        answer= new HashMap<String, String>();
    }

    public String getanswer(String qs){
        return answer.get(qs);
    }

    //获取当前天气情况
    public void putmapwendu(){
        Temperature temperature = TemperatureServiceImpl.getLastTemperature(1);
        answer.put("温度","当前实时温度"+temperature.getTemperature()+"摄氏度");
    }

    //获取天气预报
    public void putmapseven(){
        List<Sevenday> sevendayList = SevendayServiceImpl.getSevenDay();
        answer.put("天气","今天" + sevendayList.get(0).getDate() + "天气" + sevendayList.get(0).getTq() + "，温度" + sevendayList.get(0).getWd() + ",风力" + sevendayList.get(0).getFl() + "");
        answer.put("预报","明天" + sevendayList.get(1).getDate() + "天气" + sevendayList.get(1).getTq() + "，温度" + sevendayList.get(1).getWd() + ",风力" + sevendayList.get(1).getFl() + "");
        answer.put("后天","后天" + sevendayList.get(2).getDate() + "天气" + sevendayList.get(2).getTq() + "，温度" + sevendayList.get(2).getWd() + ",风力" + sevendayList.get(2).getFl() + "");
        answer.put("大后天","大后天" + sevendayList.get(3).getDate() + "天气" + sevendayList.get(3).getTq() + "，温度" + sevendayList.get(3).getWd() + ",风力" + sevendayList.get(3).getFl() + "");
    }

    //随机新闻
    public void putmapxinwensj(){
        List<News> newsList = NewsServiceImpl.getAllNews();
        answer.put("随机1",newsList.get(1).getMdftime()+"来自" + newsList.get(1).getAnthor() + "的" + newsList.get(1).getType() + "新闻：" + newsList.get(1).getContext() + "");
        answer.put("随机3",newsList.get(3).getMdftime()+"来自" + newsList.get(3).getAnthor() + "的" + newsList.get(3).getType() + "新闻：" + newsList.get(3).getContext() + "");
        answer.put("随机5",newsList.get(5).getMdftime()+"来自" + newsList.get(5).getAnthor() + "的" + newsList.get(5).getType() + "新闻：" + newsList.get(5).getContext() + "");
        answer.put("随机7",newsList.get(7).getMdftime()+"来自" + newsList.get(7).getAnthor() + "的" + newsList.get(7).getType() + "新闻：" + newsList.get(7).getContext() + "");
        answer.put("随机9",newsList.get(9).getMdftime()+"来自" + newsList.get(9).getAnthor() + "的" + newsList.get(9).getType() + "新闻：" + newsList.get(9).getContext() + "");
        answer.put("随机11",newsList.get(11).getMdftime()+"来自" + newsList.get(11).getAnthor() + "的" + newsList.get(11).getType() + "新闻：" + newsList.get(11).getContext() + "");
        answer.put("随机2",newsList.get(2).getMdftime()+"来自" + newsList.get(2).getAnthor() + "的" + newsList.get(2).getType() + "新闻：" + newsList.get(2).getContext() + "");
        answer.put("随机4",newsList.get(4).getMdftime()+"来自" + newsList.get(4).getAnthor() + "的" + newsList.get(4).getType() + "新闻：" + newsList.get(4).getContext() + "");
        answer.put("随机6",newsList.get(6).getMdftime()+"来自" + newsList.get(6).getAnthor() + "的" + newsList.get(6).getType() + "新闻：" + newsList.get(6).getContext() + "");
        answer.put("随机10",newsList.get(10).getMdftime()+"来自" + newsList.get(10).getAnthor() + "的" + newsList.get(10).getType() + "新闻：" + newsList.get(10).getContext() + "");
        answer.put("随机14",newsList.get(14).getMdftime()+"来自" + newsList.get(14).getAnthor() + "的" + newsList.get(14).getType() + "新闻：" + newsList.get(14).getContext() + "");

    }

    //普通新闻
    public void putmapxinwen(){

        List<News> newsList = NewsServiceImpl.getAllNews();
        answer.put("体育",newsList.get(8).getMdftime()+"来自" + newsList.get(8).getAnthor() + "的" + newsList.get(8).getType() + "新闻：" + newsList.get(8).getContext() + "");
        answer.put("娱乐",newsList.get(12).getMdftime()+"来自" + newsList.get(12).getAnthor() + "的" + newsList.get(12).getType() + "新闻：" + newsList.get(12).getContext() + "");
        answer.put("新闻",newsList.get(0).getMdftime()+"来自" + newsList.get(0).getAnthor() + "的" + newsList.get(0).getType() + "新闻：" + newsList.get(0).getContext() + "");

    }

    //获取全部的店铺
    public void putmapsdian(){
        List<Shop> shops = shopService.getAlllist();
        answer.put("距离近","距离最近的店家是"+shops.get(0).getsName()+"，地址是"+shops.get(0).getSaddress());
        answer.put("距离远","距离最远的店家是"+shops.get(4).getsName()+"，地址是"+shops.get(4).getSaddress());

        answer.put("消费低","人均消费最低店家是"+shops.get(0).getsName()+"，人均消费"+shops.get(0).getAvgprice()+"地址在"+shops.get(0).getSaddress());
        answer.put("消费高","人均消费最高店家是"+shops.get(4).getsName()+"，人均消费"+shops.get(4).getAvgprice()+"地址在"+shops.get(4).getSaddress());

        answer.put("评分高","评分最高的店家是"+shops.get(0).getsName()+"，人均评分"+shops.get(0).getAvgcomment()+"评论人数"+shops.get(0).getPcount());
        answer.put("评分低","评分最低的店家是"+shops.get(4).getsName()+"，人均评分"+shops.get(4).getAvgcomment()+"评论人数"+shops.get(4).getPcount());

    }





}
