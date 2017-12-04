package com.yhl.laoyou.common.dto.practitioner.healthArchive;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PhysicalExaminationTemplateDTO {

    @JSONField(name = "physicalExaminationTemplateId")
    private String physicalExaminationTemplateId;

    @JSONField(name = "physicalExaminationTemplateName")
    private String physicalExaminationTemplateName;

    @JSONField(name = "physicalExaminationTemplateIcon")
    private String physicalExaminationTemplateIcon;

    @JSONField(name = "physicalExaminationTemplateData")
    private String physicalExaminationTemplateData;

    @JSONField(name = "updateDate")
    private Date updateDate;

    public String getPhysicalExaminationTemplateId() {
        return physicalExaminationTemplateId;
    }

    public void setPhysicalExaminationTemplateId(String physicalExaminationTemplateId) {
        this.physicalExaminationTemplateId = physicalExaminationTemplateId;
    }

    public String getPhysicalExaminationTemplateName() {
        return physicalExaminationTemplateName;
    }

    public void setPhysicalExaminationTemplateName(String physicalExaminationTemplateName) {
        this.physicalExaminationTemplateName = physicalExaminationTemplateName;
    }

    public String getPhysicalExaminationTemplateIcon() {
        return physicalExaminationTemplateIcon;
    }

    public void setPhysicalExaminationTemplateIcon(String physicalExaminationTemplateIcon) {
        this.physicalExaminationTemplateIcon = physicalExaminationTemplateIcon;
    }

    public String getPhysicalExaminationTemplateData() {
        return physicalExaminationTemplateData;
    }

    public void setPhysicalExaminationTemplateData(String physicalExaminationTemplateData) {
        this.physicalExaminationTemplateData = physicalExaminationTemplateData;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}