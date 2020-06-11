package com.treehole.demo;

import com.treehole.demo.entity.CommentDTO;
import com.treehole.demo.entity.PaginationDTO;
import com.treehole.demo.entity.QuestionVo;
import com.treehole.demo.service.CommentService;
import com.treehole.demo.service.NotificationService;
import com.treehole.demo.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CommentService commentService;

    //转到主界面，根据页面、页面大小、搜索索引
    @Test
    void index() {
        int page=1; //默认第一页
        String search="";//默认搜索为空
        int size = 5; //默认大小为5
        PaginationDTO pagination = questionService.findQuestionList(search,page,size);
    }

    //测试我的提问（账户ID,页面，页面大小）
    @Test
    void myQuestions() {
        int page=1; //默认第一页
        String userId="2250432111@qq.com"; //使用默认账户ID测试
        int size = 5; //默认大小为5
        PaginationDTO paginationDTO = questionService.findQuestionListById(userId,page,size);
    }

    //测试最新回复（账户ID,页面，页面大小）
    @Test
    void myReplies() {
        int page=1; //默认第一页
        String userId="2250432111@qq.com"; //使用默认账户ID测试
        int size = 5; //默认大小为5
        PaginationDTO paginationDTO = notificationService.findNoticeListById(userId,page,size);
    }

    //测试未读的信息数（账户ID,页面，页面大小）
    @Test
    void myunreadReplies() {
        int page=1; //默认第一页
        String userId="2250432111@qq.com"; //使用默认账户ID测试
        int size = 5; //默认大小为5
        Integer unreadCount = notificationService.unreadCount(userId);
    }

    //测试根据问题的id转入页面,以及输出评论改问题的列表
    @Test
    void readQuestionById() {
        int page=1; //默认第一页
        int id = 15; //默认测试问题id为15的页面
        int size = 5; //默认大小为5
        QuestionVo  questionVo = questionService.getById(id);
        List<QuestionVo> relationQuestions = questionService.selectByTag(questionVo);
        List<CommentDTO> comments =  commentService.listByQuestionId(id,1);
        //累加浏览
        questionService.incView(id);
    }

    //测试热门话题列表
    @Test
    void hotquestions() {

        List<QuestionVo> welcomequestion = questionService.selectByCommentCount();

    }

}
