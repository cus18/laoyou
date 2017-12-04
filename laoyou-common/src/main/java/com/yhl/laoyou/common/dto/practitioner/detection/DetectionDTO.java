package com.yhl.laoyou.common.dto.practitioner.detection;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class DetectionDTO<T> {

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "elderName")
    private String elderName;

    @JSONField(name = "detectionType")
    private String detectionType;

    @JSONField(name = "detectionData")
    private List<T> detectionData;

    @JSONField(name = "updateDate")
    private Date updateDate;

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

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    public List<T> getDetectionData() {
        return detectionData;
    }

    public void setDetectionData(List<T> detectionData) {
        this.detectionData = detectionData;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}