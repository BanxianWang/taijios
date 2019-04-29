package com.sghy1801.controller;


import com.alibaba.fastjson.JSON;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @param session
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(HttpSession session, String username,
                        String password, Model model) {
        User loginUser = userService.login(username, password);
        String result = "";
        if (loginUser != null) {
            // 登录成功
            session.setAttribute("loginUser", loginUser);
            result = "main";
        } else {
            model.addAttribute("loginFlag", "error");
            result = "forward:index.jsp"; // 破坏视图解析器的跳转
        }
        return result;
    }
    // 登出
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:index.jsp";
    }
    /**
     * 查询用户列表
     * @param currentPage
     * @return
     */
    @RequestMapping("/jsp/user/userlist")
    @ResponseBody
    public Object userList(int currentPage) {
        //获取用户列表
        List<User> list = userService.listUser(currentPage);
        //获取用户记录数
        int count = userService.countUser();
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
    public String deleteUser(int id,Model model){
        int count = userService.deleteUser(id);
        if(count > 0){
            model.addAttribute("deleteFlag", "ok");
        }else{
            model.addAttribute("deleteFlag", "error");
        }
        return "userlist";
    }

    @RequestMapping("/toupdateuser")
    public String toupdateuser(int id,Model model){
        User u = userService.findById(id);
        model.addAttribute("user",u);
        return "user/updateuser";
    }
}
