package com.treehole.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treehole.demo.entity.*;
import com.treehole.demo.entity.enums.NotificationEnum;
import com.treehole.demo.entity.enums.NotificationStatusEnum;
import com.treehole.demo.exception.CustomizeErrorCode;
import com.treehole.demo.exception.CustomizeException;
import com.treehole.demo.mapper.NotificationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService extends ServiceImpl<NotificationMapper, Notification> {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserService userService;

    public PaginationDTO findNoticeListById(String userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO =  new PaginationDTO<>();
        //总的数据数
        Integer totalCount = notificationMapper.totalCount(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page < 1){
            page = 1;
        }
        if(page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //size*(i-1)
        Integer offset = size * (page-1);

        List<Notification> notifications = notificationMapper.listNotificationById(userId,offset,size);

        if(notifications.size()==0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for(Notification notification : notifications){

            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);  //复制相同的属性
            notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));
            notificationDTOList.add(notificationDTO);
        }
        //把所有的列表放进去
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;

    }

    //未读信息数
    public Integer unreadCount(String id) {
        return notificationMapper.unreadCount(id);
    }

    //读信息
    public NotificationDTO read(Integer id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateById(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);  //复制相同的属性
        notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));
        return notificationDTO;

    }
}
