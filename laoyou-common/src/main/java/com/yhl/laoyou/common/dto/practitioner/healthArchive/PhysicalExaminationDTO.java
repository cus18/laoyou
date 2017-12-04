package com.yhl.laoyou.common.dto.practitioner.healthArchive;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PhysicalExaminationDTO {

    @JSONField(name = "physicalExaminationId")
    private String physicalExaminationId;

    @JSONField(name = "physicalExaminationName")
    private String physicalExaminationName;

    @JSONField(name = "updateDate")
    private String updateDate;

    @JSONField(name = "providerId")
    private String providerId;

    @JSONField(name = "providerName")
    private String providerName;

    @JSONField(name = "providerType")
    private String providerType;

    @JSONField(name = "physicalExaminationData")
    private String physicalExaminationData;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "elderName")
    private String elderName;

    public String getPhysicalExaminationId() {
        return physicalExaminationId;
    }

    public void setPhysicalExaminationId(String physicalExaminationId) {
        this.physicalExaminationId = physicalExaminationId;
    }

    public String getPhysicalExaminationName() {
        return physicalExaminationName;
    }

    public void setPhysicalExaminationName(String physicalExaminationName) {
        this.physicalExaminationName = physicalExaminationName;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getPhysicalExaminationData() {
        return physicalExaminationData;
    }

    public void setPhysicalExaminationData(String physicalExaminationData) {
        this.physicalExaminationData = physicalExaminationData;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}