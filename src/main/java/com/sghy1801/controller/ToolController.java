package com.sghy1801.controller;

import com.alibaba.fastjson.JSONObject;
import com.sghy1801.util.AA;
import com.sghy1801.util.TTS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class ToolController {

    @RequestMapping("sevenday")
    @ResponseBody
    public String get(String path){
        Process res;//此类用于创建操作系统进程
        try {

            //数组第2位开始放入参数 0，1，》2，3.。。。。
            String[] pyargs = new String[] { "python", path};

            Process proc = Runtime.getRuntime().exec(pyargs);// 执行python脚本文件
            //通过输入流 获取python的返回值
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {//读取python的返回值
                System.out.println(line);
            }

            in.close();//关闭
            proc.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "true";
    }


    @RequestMapping(value = "getTTS",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getTTS(String str,HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("问题是：="+str);
        String ans = "";//机器回答
        if(Pattern.compile(new String("温度".getBytes(),"utf-8")).matcher(str).find()){//Str字符串中出现字符串“温度”五个字符中的任何一个，那么则返回true
            ans="当前温度是21.234摄氏度！";
        }else if (Pattern.compile(new String("天气".getBytes(),"utf-8")).matcher(str).find()){
            ans="当前天气是晴天";
        }else if (Pattern.compile(new String("box".getBytes(),"utf-8")).matcher(str).find()){
            ans="动次打次动次打次动次打次动次打次动次打次动次打次动次打次";
        }else if (Pattern.compile(new String("项目".getBytes(),"utf-8")).matcher(str).find()){
            ans="在team（团队）这个单词中没有 “I” （我），意在强调团队合作的重要性。虽然IoT中有“I”(我)，但是在物联网这个复杂领域中，真正的成功需要团队紧密合作这种合作关系的美妙之处在于它将逐渐演化成我们所谓的“共享经济”，实现合作伙伴和客户共同创新并共同创造产品，而且，随着“共享经济”的发展，我们将看到物联网市场取得更大进步和更全面的成功——这就是最好的团队合作。";
        }else{
            ans="不知道你说的是什么？";
        }

        String filename = "";
        String json ="";
        Map<String,Object> param = new HashMap<>();
        try {
            TTS a = new TTS("X9PvsnlQsXI1oPkj" );
            a.process(ans);
            filename = AA.getOut();
            param.put("result","http://192.168.43.109:9999/statics/tts"+filename+".wav");
            json = JSONObject.toJSONString(param);
            System.out.println(json);
            return "successCallback5("+json+")";
        }catch (Exception e){}
        return "successCallback5("+json+")";
    }

}
