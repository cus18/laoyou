package com.yhl.laoyou.common.dto.activity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.dto.UserInfoDTO;

import java.util.Date;

public class AttendedActivityDTO {

    //活动ID号
    @JSONField(name = "activityId")
    private String activityId;

    //围绕此活动生成的群ID号
    @JSONField(name = "activityGroupId")
    private String activityGroupId;

    //活动的logo图片URL
    @JSONField(name = "activityLogo")
    private String activityLogo;

    //活动的名称
    @JSONField(name = "activityName")
    private String activityName;

    //活动参与的人数
    @JSONField(name = "activityAttendedNum")
    private String activityAttendedNum;

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

    public String getActivityAttendedNum() {
        return activityAttendedNum;
    }

    public void setActivityAttendedNum(String activityAttendedNum) {
        this.activityAttendedNum = activityAttendedNum;
    }

    public String getActivityGroupId() {
        return activityGroupId;
    }

    public void setActivityGroupId(String activityGroupId) {
        this.activityGroupId = activityGroupId;
    }
}