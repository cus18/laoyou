package com.yhl.laoyou.modules.notificationRemindService.dao;

import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindTemplateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 提醒模板
 */

@MyBatisDao
@Repository
public interface RemindTemplateDao {

    Integer addRemindTemplate(RemindTemplateEntity remindTemplateEntity);

    RemindTemplateEntity getRemindTemplateEntityByID(@Param("ID") String remindTemplateID);

    Page getRemindTemplateEntityList(RemindTemplateEntity remindTemplateEntity,Page page);

    Integer updateRemindTemplate(RemindTemplateEntity remindTemplateEntity);

    Integer deleteRemindTemplate(String id);

}
