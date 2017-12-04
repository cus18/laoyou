package com.yhl.laoyou.common.dto.course;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class OnlineCourseDataDTO {

    //在线课程中某个课程的子课程id
    @JSONField(name = "id")
    private Integer id;

    //在线课程id
    @JSONField(name = "onlineCourseId")
    private Integer onlineCourseId;

    //在线课程中某个课程的播放URL
    @JSONField(name = "onlineCourseURL")
    private String onlineCourseURL;

    //在线课程中某个课程的时长
    @JSONField(name = "onlineCourseDuration")
    private String onlineCourseDuration;

    //在线课程中某个课程的子课程名称
    @JSONField(name = "onlineCourseName")
    private String onlineCourseName;

    //在线课程中某个课程的子课程的学习状态
    /**
     * 0 表示还没有学习过
     * 1 表示已经学习完毕
     * 2 表示正在学习过程中
     * **/
    @JSONField(name = "onlineCourseStatus")
    private String onlineCourseStatus;

    public String getOnlineCourseURL() {
        return onlineCourseURL;
    }

    public void setOnlineCourseURL(String onlineCourseURL) {
        this.onlineCourseURL = onlineCourseURL;
    }

    public String getOnlineCourseDuration() {
        return onlineCourseDuration;
    }

    public void setOnlineCourseDuration(String onlineCourseDuration) {
        this.onlineCourseDuration = onlineCourseDuration;
    }

    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer onlineCourseId) {
        this.onlineCourseId = onlineCourseId;
    }

    public String getOnlineCourseName() {
        return onlineCourseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOnlineCourseName(String onlineCourseName) {
        this.onlineCourseName = onlineCourseName;
    }

    public String getOnlineCourseStatus() {
        return onlineCourseStatus;
    }

    public void setOnlineCourseStatus(String onlineCourseStatus) {
        this.onlineCourseStatus = onlineCourseStatus;
    }
}