package com.treehole.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treehole.demo.entity.PaginationDTO;
import com.treehole.demo.entity.Question;
import com.treehole.demo.entity.QuestionVo;
import com.treehole.demo.entity.User;
import com.treehole.demo.exception.CustomizeErrorCode;
import com.treehole.demo.exception.CustomizeException;
import com.treehole.demo.mapper.QuestionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService extends ServiceImpl<QuestionMapper, Question> {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    //查询所有的questionVo对象
    public PaginationDTO findQuestionList(String search,Integer page, Integer size){

        Integer totalCount = 0;
        //搜索功能
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
            System.out.println(search);
            totalCount = questionMapper.countBySearch(search);
        } else {
            //总的数据数
            totalCount = questionMapper.count();
        }
         PaginationDTO paginationDTO =  new PaginationDTO();
         //总的数据数
         //Integer totalCount = questionMapper.count();

         paginationDTO.setPagination(totalCount,page,size);
         if(page < 1){
             page = 1;
         }
         if(page > paginationDTO.getTotalPage()){
             page = paginationDTO.getTotalPage();
         }

         //size*(i-1)
         Integer offset = size * (page-1);

         List<Question> questions = new ArrayList<>();

         if(StringUtils.isNotBlank(search)) {
             questions = questionMapper.selectBySearch(search,offset,size);
         } else {
             questions = questionMapper.listQuestion(offset,size);
         }

         //List<Question> questions = questionMapper.listQuestion(offset,size);

         List<QuestionVo> questionVoList = new ArrayList<>();

         for(Question question : questions){
             User user = userService.getById(question.getCreator());
             QuestionVo questionVo = new QuestionVo();
             BeanUtils.copyProperties(question,questionVo);  //复制相同的属性
             questionVo.setUser(user);
             questionVoList.add(questionVo);
         }

         //把所有的列表放进去
         paginationDTO.setData(questionVoList);
         return paginationDTO;
    }


    //查询固定的id的所有questionVo对象
    public PaginationDTO findQuestionListById(String userId, Integer page, Integer size) {

        PaginationDTO paginationDTO =  new PaginationDTO();
        //总的数据数
        Integer totalCount = questionMapper.countById(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page < 1){
            page = 1;
        }
        if(page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //size*(i-1)
        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.listQuestionById(userId,offset,size);
        List<QuestionVo> questionVoList = new ArrayList<>();
        for(Question question : questions){
            User user = userService.getById(question.getCreator());
            QuestionVo questionVo = new QuestionVo();
            BeanUtils.copyProperties(question,questionVo);  //复制相同的属性
            questionVo.setUser(user);
            questionVoList.add(questionVo);
        }
        //把所有的列表放进去
        paginationDTO.setData(questionVoList);
        return paginationDTO;
    }


    public QuestionVo getById(Integer id){

        Question question = questionMapper.getById(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question,questionVo);
        User user = userService.getById(question.getCreator());
        questionVo.setUser(user);
        return questionVo;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.updateById(question);
        }
    }

    //增加浏览数
    public void incView(Integer id) {
        questionMapper.updateViewCountById(id);
    }

    //增加评论数
    public void incCommentCount(Integer id){
        questionMapper.incCommentCount(id);
    }

    //根据标签查询相关的问题
    public List<QuestionVo> selectByTag(QuestionVo questionVo){
        if(StringUtils.isBlank(questionVo.getTag())){
            return new ArrayList<>();
        }
        String tag = questionVo.getTag().replace(',','|').replace('，','|');
        List<Question> list = questionMapper.selectByTag(questionVo.getId(),tag);
        List<QuestionVo> questionVoList = new ArrayList<>();
        for(Question question : list){
            QuestionVo questionVo1 = new QuestionVo();
            BeanUtils.copyProperties(question,questionVo1);
            User user = userService.getById(question.getCreator());
            questionVo1.setUser(user);
            questionVoList.add(questionVo1);
        }
       return questionVoList;
    }

    //查询评论数最多的question
    public List<QuestionVo> selectByCommentCount() {
        List<Question> Welquestions = questionMapper.listByCommentsCount();
        List<QuestionVo> welquestionVoList = new ArrayList<>();
        for(Question question : Welquestions){
            QuestionVo questionVo1 = new QuestionVo();
            BeanUtils.copyProperties(question,questionVo1);
            User user = userService.getById(question.getCreator());
            questionVo1.setUser(user);
            welquestionVoList.add(questionVo1);
        }
        return welquestionVoList;
    }
}
