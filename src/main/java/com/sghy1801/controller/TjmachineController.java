package com.sghy1801.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.Tjmachine;
import com.sghy1801.service.TjmachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机器类控制器
 */


@Controller
@RequestMapping("/jsp")
public class TjmachineController {
    @Autowired
    private TjmachineService service;

    Tjmachine tjmachine = new Tjmachine();


    /**
     * 获取所有机器信息
     * @param id
     * @param city
     * @return
     */
    @RequestMapping(value = "/getAllMachines")
    public @ResponseBody String getAllMachines(String id,String city){
        //将获取的值放入map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",0);
        if (id==""){
            map.put("id",null);
        }else {
            map.put("id",id);
        }
        if (city==""){
            map.put("city",null);
        }else {
            map.put("city",city);
        }
        //获取机器列表
        List<Tjmachine> tjmachines = service.getAllMachines(map);
        //转换成json格式
        String j = JSON.toJSONString(tjmachines);

        return j;
    }


    /**
     * 更新机器信息
     * @param city
     * @param temperature
     * @param usetime
     * @param runningstate
     * @return
     */
    @RequestMapping(value = "/updateMachineInfo")
    public @ResponseBody String updateMachineInfo(String city,int temperature,int usetime,int runningstate){
        //将获取的值放入tjmachine对象中
        tjmachine.setCity(city);
        tjmachine.setUsetime(usetime);
        tjmachine.setRunningstate(runningstate);
        tjmachine.setTemperature(temperature);
        //进行更新操作
        int count = service.updateMachineInfo(tjmachine);
        //如果成功返回true
        if (count==1)return "true";
        //失败返回false
        return "false";
    }


    /**
     * 新增机器
     * @param city
     * @return
     */
    @RequestMapping(value = "/addMachine")
    public @ResponseBody String addMachine(String city){

        tjmachine.setCity(city);
        //进行新增机器操作
        int count = service.addMachine(tjmachine);
        //成功返回true
        if (count==1)return "true";
        return "false";
    }

    /**
     * 删除机器
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMachine")
    public @ResponseBody String delMachine(String id){
        //根据ID值进行删除操作
        int count = service.delMachine(Integer.parseInt(id));
        if (count==1)return "true";
        return "false";
    }

    /**
     * 根据用户查找其拥有的机器
     * @param userid
     * @param response
     * @return
     */

    @RequestMapping(value = "/getOneByUserId")
    @ResponseBody
    public String getOneByUserId(Integer userid,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = new JSONObject();
        //根据用户查找机器
        Tjmachine tj = service.getOneByUserId(userid);
        jsonObject.put("temperature",tj.getTemperature());
        return "successCallback1(" + jsonObject.toJSONString() + ")";
    }
}
