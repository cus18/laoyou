package com.yhl.laoyou.modules.activityService.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/8/15.
 */
public class ActivityFavorite {

    private String  id;
    private String activityID;
    private String  sysElderUserID;
    private Date    createDate;
    private Date    updateDate;
    private String  delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getSysElderUserID() {
        return sysElderUserID;
    }

    public void setSysElderUserID(String sysElderUserID) {
        this.sysElderUserID = sysElderUserID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
