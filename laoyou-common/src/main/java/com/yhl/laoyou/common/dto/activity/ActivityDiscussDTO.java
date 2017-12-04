package com.yhl.laoyou.common.dto.activity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ActivityDiscussDTO {

    //此条评论的对应的活动ID
    @JSONField(name = "activityId")
    private String activityId;

    //此条评论的评论者的logo
    @JSONField(name = "elderLogo")
    private String elderLogo;

    //此条评论的评论者的名称
    @JSONField(name = "elderName")
    private String elderName;

    //此条评论的评论者的ID号
    @JSONField(name = "elderId")
    private String elderId;

    //此条评论的创建时间
    @JSONField(name = "discussDate")
    private Date discussDate;

    //此条评论的创建时间
    @JSONField(name = "discussContent")
    private String discussContent;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getElderLogo() {
        return elderLogo;
    }

    public void setElderLogo(String elderLogo) {
        this.elderLogo = elderLogo;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public Date getDiscussDate() {
        return discussDate;
    }

    public void setDiscussDate(Date discussDate) {
        this.discussDate = discussDate;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }
}