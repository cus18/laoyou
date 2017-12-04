package com.yhl.laoyou.common.dto.practitioner.healthServicePackage;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HealthServicePackageDTO {

    @JSONField(name = "servicePackageId")
    private String servicePackageId;

    @JSONField(name = "servicePackageTemplateId")
    private String servicePackageTemplateId;

    @JSONField(name = "servicePackageTemplateName")
    private String servicePackageTemplateName;

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "firstParty")
    private FirstPartyDTO firstParty;

    @JSONField(name = "secondPart")
    private SecondPartyDTO secondParty;

    @JSONField(name = "updateDate")
    private Date updateDate;

    @JSONField(name = "contractNo")
    private String contractNo;

    @JSONField(name = "status")
    private String status;

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getServicePackageTemplateId() {
        return servicePackageTemplateId;
    }

    public void setServicePackageTemplateId(String servicePackageTemplateId) {
        this.servicePackageTemplateId = servicePackageTemplateId;
    }

    public String getServicePackageTemplateName() {
        return servicePackageTemplateName;
    }

    public void setServicePackageTemplateName(String servicePackageTemplateName) {
        this.servicePackageTemplateName = servicePackageTemplateName;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public FirstPartyDTO getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(FirstPartyDTO firstParty) {
        this.firstParty = firstParty;
    }

    public SecondPartyDTO getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(SecondPartyDTO secondParty) {
        this.secondParty = secondParty;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}