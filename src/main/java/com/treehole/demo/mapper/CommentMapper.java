package com.treehole.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.treehole.demo.entity.Comment;
import com.treehole.demo.entity.CommentDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    @Select("Select * From comment Where parent_id=#{id} and type=#{type}")
    List<Comment> listByQuestionId(Integer id,Integer type);

    @Select("Update comment Set comment_count = comment_count + 1 Where id=#{id}")
    void incCommentCount(Integer id);

    @Select("Update comment Set like_count = like_count + 1 Where id=#{id}")
    void incLikeCount(Integer id);

}
