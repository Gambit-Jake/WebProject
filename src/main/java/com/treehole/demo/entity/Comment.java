package com.treehole.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("comment")
public class Comment extends Model<Comment> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String commentator;
    private Long gmtCreate;
    private Integer likeCount;
    private String content;
    private Long gmtModified;
    private Integer commentCount;
}
