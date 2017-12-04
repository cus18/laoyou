/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.notificationRemindService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.NotificationUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 消息用户
 */
@MyBatisDao
@Repository
public interface NotificationUserDao {

    Integer addNotificationUser(NotificationUserEntity notificationUser);

    Integer updateNotificationUser(String notificationUserID);

    Integer getNotificationByUnread(@Param("sysElderUserID") String sysElderUserID);


}
