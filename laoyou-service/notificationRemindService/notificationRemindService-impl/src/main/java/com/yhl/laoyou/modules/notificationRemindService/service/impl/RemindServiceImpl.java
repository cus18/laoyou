package com.yhl.laoyou.modules.notificationRemindService.service.impl;

import com.yhl.laoyou.common.dto.notification.NotificationMessageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindTemplateDao;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindUserDao;
import com.yhl.laoyou.modules.notificationRemindService.entity.RemindTemplateEntity;
import com.yhl.laoyou.modules.notificationRemindService.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/17.
 */
@Service
@Transactional(readOnly = false)
public class RemindServiceImpl implements RemindService {

    @Autowired
    private RemindDao remindDao;

    @Autowired
    private RemindUserDao remindUserDao;

    @Autowired
    private RemindTemplateDao remindTemplateDao;

    @Override
    public List<NotificationMessageDTO> getRemindListBySysElderUserID(String sysElderUserID, Integer limit) {
        return remindDao.getRemindListBySysElderUserID(sysElderUserID,limit);
    }

    @Override
    public Integer getRemindByUnread(String sysElderUserID) {
        return remindUserDao.getRemindByUnread(sysElderUserID);
    }

    @Override
    public NotificationMessageDTO getRemindByID(String remindID) {
        return remindDao.getRemindByID(remindID);
    }

    @Override
    public void updateRemindStatus(String remindID) {
        remindUserDao.updateRemindUser(remindID);
    }

    //********机构版接口
    @Override
    public Integer addRemindTemplate(RemindTemplateEntity remindTemplateEntity) {
        return remindTemplateDao.addRemindTemplate(remindTemplateEntity);
    }

    @Override
    public RemindTemplateEntity getRemindTemplateEntityByID(String remindTemplateID) {
        return remindTemplateDao.getRemindTemplateEntityByID(remindTemplateID);
    }

    @Override
    public Page getRemindTemplateEntityList(RemindTemplateEntity remindTemplateEntity, Page page) {
        return remindTemplateDao.getRemindTemplateEntityList(remindTemplateEntity,page);
    }

    @Override
    public Integer updateRemindTemplate(RemindTemplateEntity remindTemplateEntity) {
        return remindTemplateDao.updateRemindTemplate(remindTemplateEntity);
    }

    @Override
    public Integer deleteRemindTemplate(String id) {
        return remindTemplateDao.deleteRemindTemplate(id);
    }
}
