package com.yhl.laoyou.modules.activityService.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by zbm84 on 2017/7/21.
 */
public class Activity {

    private Integer id;
    private String  banner;
    private String  title;
    private String    startDate;
    private String    endDate;
    private String  address;
    private String  description;
    private String  type;
    private String  sysUserID;
    private String  userType;
    private String  easemobGroupID;
    private Date    createTime;
    private Date    updateTime;
    private String  status;
    private String  peopleNum;
    private String  del_flag;

    private List<ActivityUser> activityUsers;

    public Integer getId() {

        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSysUserID() {
        return sysUserID;
    }

    public void setSysUserID(String sysUserID) {
        this.sysUserID = sysUserID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEasemobGroupID() {
        return easemobGroupID;
    }

    public void setEasemobGroupID(String easemobGroupID) {
        this.easemobGroupID = easemobGroupID;
    }

    public List<ActivityUser> getActivityUsers() {
        return activityUsers;
    }

    public void setActivityUsers(List<ActivityUser> activityUsers) {
        this.activityUsers = activityUsers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}
