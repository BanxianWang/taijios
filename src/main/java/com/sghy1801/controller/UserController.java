package com.sghy1801.controller;


import com.alibaba.fastjson.JSON;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session) {
        User loginUser = service.login(username, password);
        String result = "";
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            result = "main";
        } else {
            model.addAttribute("loginFlag", "error");
            result = "forward:index";
        }
        return result;
    }

    @RequestMapping("/jsp/user/userlist")
    @ResponseBody
    public Object userList(String name, String userorman, int currentPage) {
        //获取用户列表
        List<User> list = service.listUser(name, userorman, currentPage);
        //获取用户记录数
        int count = service.countUser(name, userorman);
        //总页数
        int totalPage = count % 4 == 0 ? count / 4 : count / 4 + 1;
        //包装数据
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("list", list);
        param.put("count", count);
        param.put("totalPage", totalPage);
        return JSON.toJSONString(param);
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        int count =service.deleteUser(id);
        if (count==1)return "true";
        return "false";
    }


}
