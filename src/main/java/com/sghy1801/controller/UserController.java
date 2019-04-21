package com.sghy1801.controller;

import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wrm
 * @create 2019-04-21 19:47
 */
@Controller
@RequestMapping("/jsp")
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String UserLogin(@RequestParam(value = "username",required = true) String username
                            , @RequestParam(value = "password",required = true) String password,
                            HttpServletRequest request){
        int flag = service.getUser(username,password);
        //0代表登录失败没有该用户 1代表登录失败密码错误 2代表登录成功
        if(flag ==1){
            request.setAttribute("login",1);
        }else if(flag==2){
            return  "main";
        }else if(flag == 0){
            request.setAttribute("login",0);
        }
        return  "login";
    };
}
