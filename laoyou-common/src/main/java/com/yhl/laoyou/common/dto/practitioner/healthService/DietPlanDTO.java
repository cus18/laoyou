package com.yhl.laoyou.common.dto.practitioner.healthService;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zbm84 on 2017/5/27.
 */
public class DietPlanDTO {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "elderUserID")
    private String elderUserID;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "imageURL")
    private String imageURL;


    @JSONField(name = "createDate")
    private String createDate;

    @JSONField(name = "startDate")
    private String startDate;

    @JSONField(name = "endDate")
    private String endDate;

    @JSONField(name = "createTime")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElderUserID() {
        return elderUserID;
    }

    public void setElderUserID(String elderUserID) {
        this.elderUserID = elderUserID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
