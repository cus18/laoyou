package com.yhl.laoyou.modules.activityService.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/7/27.
 */
public class ActivityDiscuss {

    private Integer id;

    private String activityID;

    private String sysUserElderID;

    private Date createDate;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getSysUserElderID() {
        return sysUserElderID;
    }

    public void setSysUserElderID(String sysUserElderID) {
        this.sysUserElderID = sysUserElderID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
