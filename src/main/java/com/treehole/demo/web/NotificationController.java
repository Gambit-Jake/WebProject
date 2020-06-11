package com.treehole.demo.web;

import com.treehole.demo.entity.NotificationDTO;
import com.treehole.demo.entity.PaginationDTO;
import com.treehole.demo.entity.User;
import com.treehole.demo.entity.enums.NotificationEnum;
import com.treehole.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/notification/{id}")
    public String profile(
            HttpSession session,
            @PathVariable(name="id") Integer id,
            Model model
    ){
        //从session中获取用户信息
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/index";
        }

        NotificationDTO notificationDTO = notificationService.read(id,user);
        if(NotificationEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/index";
        }

    }
}
