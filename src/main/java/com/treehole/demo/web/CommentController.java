package com.treehole.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.treehole.demo.entity.*;
import com.treehole.demo.exception.CustomizeErrorCode;
import com.treehole.demo.service.CommentService;
import com.treehole.demo.service.LikehistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikehistoryService likehistoryService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(
            @RequestBody CommentCreateDTO commentCreateDTO,
            HttpServletRequest request
    ){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isAllBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.saveByCheck(comment,user);

        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Integer id){
        List<CommentDTO> commentDTOS =commentService.listByQuestionId(id,2);
        return ResultDTO.okOf(commentDTOS);
    }


    //点赞
    @Transactional
    @RequestMapping("/like")
    @ResponseBody
    public String incLike(Integer id, HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Likehistory> historyQueryWrapper = new QueryWrapper<>();
        historyQueryWrapper.eq("user",user.getId());
        historyQueryWrapper.eq("likecomment",id);
        List<Likehistory> historyList = likehistoryService.list(historyQueryWrapper);
        if(historyList.isEmpty()){
            commentService.incLikeCount(id);
            Likehistory likehistory = new Likehistory();
            likehistory.setLikecomment(id);
            likehistory.setUser(user.getId());
            likehistoryService.save(likehistory);
            return  "success";
        } else {
            return  "ishave";
        }
    }
}
