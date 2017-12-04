package com.yhl.laoyou.common.dto.practitioner.healthArchive;

import com.alibaba.fastjson.annotation.JSONField;
import jxl.write.DateTime;

import java.util.Date;

public class HealthAssessmentDTO {

    @JSONField(name = "_id")
    private String _id;

    @JSONField(name = "healthAssessmentId")
    private String healthAssessmentId;

    @JSONField(name = "healthAssessmentTemplateId")
    private String healthAssessmentTemplateId;

    @JSONField(name = "healthAssessmentTemplateName")
    private String healthAssessmentTemplateName;

    @JSONField(name = "updateDate")
    private Date updateDate;

    @JSONField(name = "providerId")
    private String providerId;

    @JSONField(name = "providerName")
    private String providerName;

    @JSONField(name = "providerType")
    private String providerType;

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "elderName")
    private String elderName;

    @JSONField(name = "healthAssessmentData")
    private String healthAssessmentData;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHealthAssessmentId() {
        return healthAssessmentId;
    }

    public void setHealthAssessmentId(String healthAssessmentId) {
        this.healthAssessmentId = healthAssessmentId;
    }

    public String getHealthAssessmentTemplateId() {
        return healthAssessmentTemplateId;
    }

    public void setHealthAssessmentTemplateId(String healthAssessmentTemplateId) {
        this.healthAssessmentTemplateId = healthAssessmentTemplateId;
    }

    public String getHealthAssessmentTemplateName() {
        return healthAssessmentTemplateName;
    }

    public void setHealthAssessmentTemplateName(String healthAssessmentTemplateName) {
        this.healthAssessmentTemplateName = healthAssessmentTemplateName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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

    public String getHealthAssessmentData() {
        return healthAssessmentData;
    }

    public void setHealthAssessmentData(String healthAssessmentData) {
        this.healthAssessmentData = healthAssessmentData;
    }
}