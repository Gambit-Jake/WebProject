package com.treehole.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treehole.demo.entity.User;
import com.treehole.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {



    //@Autowired
    //private UserMapper userMapper;

    @Autowired
    private UserService userService;


    //验证用户的存在性
    public String checkUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        //User user = userMapper.selectOne(queryWrapper);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "101";  //用户不存在
        }else{
            return "102";  //用户已存在
        }
    }

    //验证邮箱是否可用
    public String checkUserById(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",email);
        //User user = userMapper.selectOne(queryWrapper);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "201";  //用户不存在
        }else{
            return "202";  //用户已存在
        }
    }

    //验证登录
    public String loginCheck(User loginUser, HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",loginUser.getId());
        User user = userService.getOne(queryWrapper);
        //User user = userMapper.selectOne(queryWrapper);
        if(loginUser.getPassword().equals(user.getPassword())){
            session.setAttribute("user",user);
            return "101";
        } else {
            return "102"; //密码错误
        }
    }


}
