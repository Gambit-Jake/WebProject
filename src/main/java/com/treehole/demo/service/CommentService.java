package com.treehole.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treehole.demo.entity.*;
import com.treehole.demo.entity.enums.CommentTypeEnum;
import com.treehole.demo.entity.enums.NotificationEnum;
import com.treehole.demo.entity.enums.NotificationStatusEnum;
import com.treehole.demo.exception.CustomizeErrorCode;
import com.treehole.demo.exception.CustomizeException;
import com.treehole.demo.mapper.CommentMapper;
import com.treehole.demo.mapper.NotificationMapper;
import com.treehole.demo.mapper.QuestionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    //保存评论
    @Transactional
    public void saveByCheck(Comment comment,User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbcomment = commentMapper.selectById(comment.getParentId());
            if(dbcomment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } else {

                //查询评论的原问题
                Question question = questionMapper.getById(dbcomment.getParentId());
                if(question==null){
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
                commentMapper.insert(comment);
                //增加评论的评论数
                commentMapper.incCommentCount(comment.getParentId());
                //增加通知
                createNotify(comment,dbcomment.getCommentator(),commentator.getName(),question.getTitle(),NotificationEnum.REPLY_COMMENT.getType(),question.getId());

            }
        } else {
            //回复问题
            Question question = questionMapper.getById(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incCommentCount(comment.getParentId());
            //增加通知
            createNotify(comment,question.getCreator(),commentator.getName(),question.getTitle(),NotificationEnum.REPLY_QUESTION.getType(),question.getId());
        }
    }

    //增加通知
    private void createNotify(Comment comment, String receiver,String notifierName,String outerTitle,Integer type,Integer outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(type);
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationService.save(notification);
    }

    public List<CommentDTO> listByQuestionId(Integer id,Integer type) {
        List<Comment> comments = commentMapper.listByQuestionId(id,type);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment :comments){
            CommentDTO commentDTO =new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            User user = userService.getById(comment.getCommentator());
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    //增加评论的评论数
    public void incCommentCount(Integer id) {
        commentMapper.incCommentCount(id);
    }

    //增加点赞的评论数
    public void incLikeCount(Integer id) {
        commentMapper.incLikeCount(id);
    }
}
