package com.yhl.laoyou.common.dto.practitioner.healthArchive;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HealthAssessmentTemplateDTO {

    @JSONField(name = "healthAssessmentTemplateId")
    private String healthAssessmentTemplateId;

    @JSONField(name = "healthAssessmentTemplateName")
    private String healthAssessmentTemplateName;

    @JSONField(name = "healthAssessmentTemplateIcon")
    private String healthAssessmentTemplateIcon;

    @JSONField(name = "healthAssessmentTemplateData")
    private String healthAssessmentTemplateData;

    @JSONField(name = "updateDate")
    private Date updateDate;

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

    public String getHealthAssessmentTemplateData() {
        return healthAssessmentTemplateData;
    }

    public void setHealthAssessmentTemplateData(String healthAssessmentTemplateData) {
        this.healthAssessmentTemplateData = healthAssessmentTemplateData;
    }

    public String getHealthAssessmentTemplateIcon() {
        return healthAssessmentTemplateIcon;
    }

    public void setHealthAssessmentTemplateIcon(String healthAssessmentTemplateIcon) {
        this.healthAssessmentTemplateIcon = healthAssessmentTemplateIcon;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}