package com.yhl.laoyou.common.dto.course;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

public class OnlineCourseDiscussDTO extends DataEntity<OnlineCourseDiscussDTO> {

    //评论id
    @JSONField(name = "discussId")
    private Integer discussId;

    //在线课程ID号
    @JSONField(name = "onlineCourseId")
    private Integer onlineCourseId;

    //评价者的ID号
    @JSONField(name = "elderId")
    private String elderId;

    //评价者的名字
    @JSONField(name = "elderName")
    private String elderName;

    //评论的内容
    @JSONField(name = "onlineCourseDiscussContent")
    private String onlineCourseDiscussContent;

    //评论的时间
    @JSONField(name = "onlineCourseDiscussDate")
    private Date onlineCourseDiscussDate;

    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer onlineCourseId) {
        this.onlineCourseId = onlineCourseId;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getOnlineCourseDiscussContent() {
        return onlineCourseDiscussContent;
    }

    public void setOnlineCourseDiscussContent(String onlineCourseDiscussContent) {
        this.onlineCourseDiscussContent = onlineCourseDiscussContent;
    }

    public Date getOnlineCourseDiscussDate() {
        return onlineCourseDiscussDate;
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public void setOnlineCourseDiscussDate(Date onlineCourseDiscussDate) {
        this.onlineCourseDiscussDate = onlineCourseDiscussDate;
    }
}