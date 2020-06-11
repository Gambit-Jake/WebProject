package com.treehole.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("likehistory")
public class Likehistory extends Model<Likehistory> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String user;
    private Integer likecomment;
}
