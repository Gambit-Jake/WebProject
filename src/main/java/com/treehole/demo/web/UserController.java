package com.treehole.demo.web;



import com.treehole.demo.entity.User;
import com.treehole.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    //转到登录界面
    @RequestMapping("/reg")
    public String reg(User user){
        return "register";
    }

    //验证用户名是否已经存在
    @ResponseBody
    @RequestMapping("/checkUserName")
    public String checkUserName(String name){
        return userService.checkUserByName(name);
    }

    //验证邮箱是否注册过
    @ResponseBody
    @RequestMapping("/checkUserId")
    public String checkUserId(String id){
        return userService.checkUserById(id);
    }

    //用户注册
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        user.setDate(new Date());
        userService.save(user);
        return "success";
    }

    //转到登录界面
    @RequestMapping("/tologin")
    public String tologin(User user){
        return "login";
    }

    //用户登录
    @ResponseBody
    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        return userService.loginCheck(user,session);
    }

    //安全退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

}
