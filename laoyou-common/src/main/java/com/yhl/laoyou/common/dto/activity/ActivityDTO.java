package com.yhl.laoyou.common.dto.activity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.Date;

public class ActivityDTO extends DataEntity<ActivityDTO>{

    //活动ID号
    @JSONField(name = "activityId")
    private String activityId;

    //活动的logo图片URL
    @JSONField(name = "activityLogo")
    private String activityLogo;

    //活动的名称
    @JSONField(name = "activityName")
    private String activityName;

    //活动的地址
    @JSONField(name = "activityAddress")
    private String activityAddress;

    //活动的开始时间 2017-06-08 10：00
    @JSONField(name = "activityStartDate")
    private Date activityStartDate;

    //活动的结束时间 2017-06-08 21：00
    @JSONField(name = "activityEndDate")
    private Date activityEndDate;

    //活动的状态
    /**
     * 0表示还没有开始
     * 1表示活动正在进行中
     * 2表示活动已经结束
     * */
    @JSONField(name = "activityStatus")
    private String activityStatus;

    //活动参与的人数
    @JSONField(name = "activityAttendedNum")
    private String activityAttendedNum;

    //活动的详细介绍信息
    /**
     * 例如：初夏踏青郊游，亲自体验园林采摘。
     * */
    @JSONField(name = "activityDetailInfo")
    private String activityDetailInfo;


    //活动发起机构的名称
    @JSONField(name = "activityOwnerName")
    private String activityOwnerName;

    //活动发起机构的logo的URL地址
    @JSONField(name = "activityOwnerLogo")
    private String activityOwnerLogo;

    //活动发起机构的介绍
    /**
     * 例如：致力于丰富老人业余生活
     * */
    @JSONField(name = "activityOwnerIntro")
    private String activityOwnerIntro;

    @JSONField(name = "activityEasemobGroupID")
    private String activityEasemobGroupID;

    private Integer peopleNum;

    private String createTime;

    private String type;

    private String hospitalID;

    Integer pageNo;
    Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityLogo() {
        return activityLogo;
    }

    public void setActivityLogo(String activityLogo) {
        this.activityLogo = activityLogo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public Date getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(Date activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public Date getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(Date activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityAttendedNum() {
        return activityAttendedNum;
    }

    public void setActivityAttendedNum(String activityAttendedNum) {
        this.activityAttendedNum = activityAttendedNum;
    }

    public String getActivityDetailInfo() {
        return activityDetailInfo;
    }

    public void setActivityDetailInfo(String activityDetailInfo) {
        this.activityDetailInfo = activityDetailInfo;
    }

    public String getActivityOwnerName() {
        return activityOwnerName;
    }

    public void setActivityOwnerName(String activityOwnerName) {
        this.activityOwnerName = activityOwnerName;
    }

    public String getActivityOwnerLogo() {
        return activityOwnerLogo;
    }

    public void setActivityOwnerLogo(String activityOwnerLogo) {
        this.activityOwnerLogo = activityOwnerLogo;
    }

    public String getActivityOwnerIntro() {
        return activityOwnerIntro;
    }

    public void setActivityOwnerIntro(String activityOwnerIntro) {
        this.activityOwnerIntro = activityOwnerIntro;
    }

    public String getActivityEasemobGroupID() {
        return activityEasemobGroupID;
    }

    public void setActivityEasemobGroupID(String activityEasemobGroupID) {
        this.activityEasemobGroupID = activityEasemobGroupID;
    }

}