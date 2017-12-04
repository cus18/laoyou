package com.yhl.laoyou.modules.activityService.entity;

/**
 * Created by zbm84 on 2017/7/21.
 */
public class ActivityUser {

    private Integer id;
    private String activityID;
    private String  sysElderUserID;
    private String  phoneNum;
    private String createDate;

    private String name;

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

    public String getSysElderUserID() {
        return sysElderUserID;
    }

    public void setSysElderUserID(String sysElderUserID) {
        this.sysElderUserID = sysElderUserID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
