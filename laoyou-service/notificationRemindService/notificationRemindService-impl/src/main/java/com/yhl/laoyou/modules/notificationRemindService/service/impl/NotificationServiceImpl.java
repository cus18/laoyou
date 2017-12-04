package com.yhl.laoyou.modules.notificationRemindService.service.impl;

import com.yhl.laoyou.common.dto.notification.ExtendMessageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.notificationRemindService.dao.NotificationDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.NotificationTemplateDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.NotificationUserDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.NotificationTemplateEntity;
import com.yhl.laoyou.modules.notificationRemindService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/17.
 */
@Service
@Transactional(readOnly = false)
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private NotificationUserDao notificationUserDao;

    @Autowired
    private NotificationTemplateDao notificationTemplateDao;

    @Override
    public List<ExtendMessageDTO> getNotificationListBySysElderUserID(String sysElderUserID,Integer limit) {
        return notificationDao.getNotificationListBySysElderUserID(sysElderUserID,limit);
    }

    @Override
    public Integer getNotificationByUnread(String sysElderUserID) {
        return notificationUserDao.getNotificationByUnread(sysElderUserID);
    }

    @Override
    public ExtendMessageDTO getNotificationByID(String notificationID) {
        return notificationDao.getNotificationByID(notificationID);
    }

    @Override
    public void updateNotificationStatus(String notificationID) {
        notificationUserDao.updateNotificationUser(notificationID);
    }

    //*************机构版使用接口

    @Override
    public Integer addNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity) {
        return notificationTemplateDao.addNotificationTemplate(notificationTemplateEntity);
    }

    @Override
    public Page getNotificationTemplateList(NotificationTemplateEntity notificationTemplateEntity,Page page) {
        return notificationTemplateDao.getNotificationTemplateList(notificationTemplateEntity,page);
    }

    @Override
    public NotificationTemplateEntity getNotificationTemplate(String id) {
        return notificationTemplateDao.getNotificationTemplateByID(id);
    }

    @Override
    public Integer updateNotificationTemplate(NotificationTemplateEntity notificationTemplateEntity) {
        return notificationTemplateDao.updateNotificationTemplate(notificationTemplateEntity);
    }

    @Override
    public Integer deleteNotificationTemplate(String id) {
        return notificationTemplateDao.deleteNotificationTemplate(id);
    }

}
