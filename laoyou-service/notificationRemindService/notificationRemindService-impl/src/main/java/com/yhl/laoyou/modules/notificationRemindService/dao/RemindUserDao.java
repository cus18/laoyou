/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.notificationRemindService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 提醒消息用户
 */
@MyBatisDao
@Repository
public interface RemindUserDao {

    Integer addRemindUser(RemindUserEntity remindUser);

    Integer updateRemindUser(String remindUserID);

    Integer getRemindByUnread(@Param("sysElderUserID") String sysElderUserID);


}
