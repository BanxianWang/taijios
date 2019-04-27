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

@Controller

public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
       User loginUser= service.login(username,password);
        String result = "";
       if(loginUser !=null){
           session.setAttribute("loginUser", loginUser);
           result= "main";
       }else{
           model.addAttribute("loginFlag", "error");
           result= "forward:index";
       }
       return result;
    }

    @RequestMapping("/jsp/user/userlist")
    @ResponseBody
    public Object userList(){
        return JSON.toJSONString(service.userList());

    }




}
