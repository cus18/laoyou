package com.yhl.laoyou.common.dto.practitioner.healthServicePackage;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HealthServicePackageTemplateDTO {

    @JSONField(name = "healthServicePackageTemplateId")
    private String healthServicePackageTemplateId;

    @JSONField(name = "healthServicePackageTemplateName")
    private String healthServicePackageTemplateName;

    @JSONField(name = "healthServicePackageTemplateData")
    private String healthServicePackageTemplateData;

    @JSONField(name = "firstPartySignature")
    private String firstPartySignature;

    @JSONField(name = "subscribeNum")
    private String subscribeNum;

    @JSONField(name = "updateDate")
    private Date updateDate;

    public String getHealthServicePackageTemplateId() {
        return healthServicePackageTemplateId;
    }

    public void setHealthServicePackageTemplateId(String healthServicePackageTemplateId) {
        this.healthServicePackageTemplateId = healthServicePackageTemplateId;
    }

    public String getHealthServicePackageTemplateName() {
        return healthServicePackageTemplateName;
    }

    public void setHealthServicePackageTemplateName(String healthServicePackageTemplateName) {
        this.healthServicePackageTemplateName = healthServicePackageTemplateName;
    }

    public String getHealthServicePackageTemplateData() {
        return healthServicePackageTemplateData;
    }

    public void setHealthServicePackageTemplateData(String healthServicePackageTemplateData) {
        this.healthServicePackageTemplateData = healthServicePackageTemplateData;
    }

    public String getFirstPartySignature() {
        return firstPartySignature;
    }

    public void setFirstPartySignature(String firstPartySignature) {
        this.firstPartySignature = firstPartySignature;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }
}