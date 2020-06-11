package com.treehole.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;



@Data
@TableName("user")
public class User extends Model<User> {

    @TableId
    @Email
    private String id;
    private String name;
    private String password;
    private Date date;
    private String imgurl;

}

