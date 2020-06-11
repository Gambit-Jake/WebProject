package com.treehole.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.treehole.demo.entity.PaginationDTO;
import com.treehole.demo.entity.Question;
import com.treehole.demo.entity.User;
import com.treehole.demo.service.NotificationService;
import com.treehole.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/profile/{action}")
    public String profile(
            HttpSession session,
            @PathVariable(name="action") String action,
            @RequestParam(name="page",defaultValue = "1") Integer page,
            @RequestParam(name="size",defaultValue = "5") Integer size,
            Model model
    ){
        //从session中获取用户信息
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/index";
        }

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.findQuestionListById(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        } else if("replies".equals(action)){

            PaginationDTO paginationDTO = notificationService.findNoticeListById(user.getId(),page,size);
            Integer unreadCount = notificationService.unreadCount(user.getId());
            session.setAttribute("unreadCount",unreadCount);
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }


        return "profile";
    }

}
