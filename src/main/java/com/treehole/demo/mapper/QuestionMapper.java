package com.treehole.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.treehole.demo.entity.Question;
import com.treehole.demo.entity.QuestionVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("Select * From question ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> listQuestion(@Param(value = "offset") Integer offset, @Param(value = "size")  Integer size);

    @Select("Select count(1) from question")
    Integer count();

    @Select("Select * From question Where creator=#{userId} ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> listQuestionById(String userId, Integer offset, Integer size);

    @Select("Select count(1) from question Where creator=#{userId}")
    Integer countById(String userId);

    @Select("Select * From question Where id=#{id}")
    Question getById(Integer id);

    @Select("Update question Set view_count = view_count + 1 Where id=#{id}")
    Question updateViewCountById(Integer id);

    @Select("Update question Set comment_count = comment_count + 1 Where id=#{id}")
    Question incCommentCount(Integer id);

    @Select("Select * From question where id!=#{id} and tag regexp #{tag}")
    List<Question> selectByTag(Integer id,String tag);

    @Select("Select count(1) from question Where tag regexp #{tag}")
    Integer countBySearch(String tag);

    @Select("Select * From question where tag regexp #{tag} ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectBySearch(String tag,Integer offset,Integer size);

    @Select("Select * from question ORDER BY (comment_count*0.7 + view_count*0.3) desc LIMIT 0,10")
    List<Question> listByCommentsCount();

}
