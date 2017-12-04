/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.notificationRemindService.dao;

import com.yhl.laoyou.common.dto.notification.NotificationMessageDTO;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 提醒
 */
@MyBatisDao
@Repository
public interface RemindDao {

    Integer addRemind(RemindEntity remind);

    List<NotificationMessageDTO> getRemindListBySysElderUserID(@Param("sysElderUserID") String sysElderUserID, @Param("limit") Integer limit);

    NotificationMessageDTO getRemindByID(@Param("ID") String remindID);

}
