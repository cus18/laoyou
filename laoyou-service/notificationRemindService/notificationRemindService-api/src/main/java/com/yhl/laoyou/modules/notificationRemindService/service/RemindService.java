package com.yhl.laoyou.modules.notificationRemindService.service;

import com.yhl.laoyou.common.dto.notification.NotificationMessageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindTemplateEntity;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/17.
 */

public interface RemindService {

    public List<NotificationMessageDTO> getRemindListBySysElderUserID(String sysElderUserID, Integer limit);

    public Integer getRemindByUnread(String sysElderUserID);

    NotificationMessageDTO getRemindByID(String notificationID);

    void updateRemindStatus(String notificationID);

    //******机构版使用接口
    Integer addRemindTemplate(RemindTemplateEntity remindTemplateEntity);

    RemindTemplateEntity getRemindTemplateEntityByID( String remindTemplateID);

    Page getRemindTemplateEntityList(RemindTemplateEntity remindTemplateEntity, Page page);

    Integer updateRemindTemplate(RemindTemplateEntity remindTemplateEntity);

    Integer deleteRemindTemplate(String id);

}
