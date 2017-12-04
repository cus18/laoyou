/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.notificationRemindService.dao;

import com.yhl.laoyou.common.dto.notification.ExtendMessageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.NotificationEntity;
import com.yhl.laoyou.modules.notificationRemindService.entity.NotificationTemplateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息
 */
@MyBatisDao
@Repository
public interface NotificationTemplateDao {

    Integer addNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity);

    Page getNotificationTemplateList(NotificationTemplateEntity notificationTemplateEntity,Page page);

    NotificationTemplateEntity getNotificationTemplateByID(String id);

    Integer updateNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity);

    Integer deleteNotificationTemplate(String id);
}
