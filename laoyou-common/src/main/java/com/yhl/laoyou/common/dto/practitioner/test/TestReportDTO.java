package com.yhl.laoyou.common.dto.practitioner.test;

import com.alibaba.fastjson.annotation.JSONField;

public class TestReportDTO {

    @JSONField(name = "reportId")
    private String reportId;

    @JSONField(name = "testReportDate")
    private String testReportDate;

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "elderName")
    private String elderName;

    @JSONField(name = "providerId")
    private String providerId;

    @JSONField(name = "providerName")
    private String providerName;

    @JSONField(name = "updateDate")
    private String updateDate;

    @JSONField(name = "reportImage")
    private String reportImage;

    @JSONField(name = "description")
    private String description;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTestReportDate() {
        return testReportDate;
    }

    public void setTestReportDate(String testReportDate) {
        this.testReportDate = testReportDate;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getReportImage() {
        return reportImage;
    }

    public void setReportImage(String reportImage) {
        this.reportImage = reportImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}