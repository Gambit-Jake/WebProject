package com.treehole.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.treehole.demo.entity.Notification;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NotificationMapper extends BaseMapper<Notification> {

    @Select("Select count(1) from notification Where receiver=#{id} And status= 0 ")
    Integer unreadCount(String id);

    @Select("Select count(1) from notification Where receiver=#{id}")
    Integer totalCount(String id);

    @Select("Select * From notification Where receiver=#{userId} ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Notification> listNotificationById(String userId, Integer offset, Integer size);

}
