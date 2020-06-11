package com.treehole.demo.web;

import com.treehole.demo.entity.User;
import com.treehole.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/personal")
public class PersonalDataController {


    @Autowired
    private UserService userService;

    @RequestMapping()
    public String toPersonal(HttpSession session){
        User user = (User) session.getAttribute("user");
        return "update_account";
    }

    @RequestMapping("/update")
    public String doUpdate(
        @RequestParam(value = "name",required = false) String name,
        @RequestParam(value = "password1",required = false) String password1,
        @RequestParam(value = "password2",required = false) String password2,
        @RequestParam(value = "url",required = false) String url,
        HttpSession session,
        Model model
    ){
        User user = (User) session.getAttribute("user");
        if(!name.equals(user.getName())&&userService.checkUserByName(name).equals("102")){
            model.addAttribute("error","该姓名已经存在，请更换!");
            return "update_account";
        }
        if(!password1.equals(password2)&&!password1.equals(user.getPassword())){
            model.addAttribute("error","两次密码不一致，请再次确认密码！");
            return "update_account";
        }
        if(url.length()>256){
            model.addAttribute("error","输入的图片地址过长~");
            return "update_account";
        }
        user.setName(name);
        user.setPassword(password1);
        user.setImgurl(url);
        userService.updateById(user);
        return "update_account";
    }




}
