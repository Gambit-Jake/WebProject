package com.treehole.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.treehole.demo.entity.PaginationDTO;
import com.treehole.demo.entity.Question;
import com.treehole.demo.entity.QuestionVo;
import com.treehole.demo.entity.User;
import com.treehole.demo.service.NotificationService;
import com.treehole.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    //转到主界面
    @RequestMapping("/index")
    public String index(Model model,HttpSession session,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name="search",required = false) String search
    ) {
        PaginationDTO pagination = questionService.findQuestionList(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        //热门问题
        List<QuestionVo> welcomequestion = questionService.selectByCommentCount();
        model.addAttribute("welcomequestions",welcomequestion);

        User user = (User) session.getAttribute("user");
        if(user != null){
            Integer unreadCount = notificationService.unreadCount(user.getId());
            session.setAttribute("unreadCount",unreadCount);
        }
        return "index";
    }



}