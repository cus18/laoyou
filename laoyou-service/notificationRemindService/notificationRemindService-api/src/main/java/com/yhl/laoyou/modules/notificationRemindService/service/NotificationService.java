package com.yhl.laoyou.modules.notificationRemindService.service;

import com.yhl.laoyou.common.dto.notification.ExtendMessageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.notificationRemindService.entity.NotificationTemplateEntity;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/17.
 */

public interface NotificationService {

    List<ExtendMessageDTO> getNotificationListBySysElderUserID(String sysElderUserID,Integer limit);

    Integer getNotificationByUnread(String sysElderUserID);

    ExtendMessageDTO getNotificationByID(String notificationID);

    void updateNotificationStatus(String notificationID);

    //*********机构版使用接口
    Integer addNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity);

    Page getNotificationTemplateList(NotificationTemplateEntity notificationTemplateEntity,Page page);

    NotificationTemplateEntity getNotificationTemplate(String id);

    Integer updateNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity);

    Integer deleteNotificationTemplate(String id);
}
