package com.yhl.laoyou.modules.healthDataService.entity;

import java.util.List;

/**
 * Created by sunxiao on 2017/5/18.
 */
public class TestReport {

    private String elderId;
    private String elderName;
    private String reportId;
    private String providerId;
    private String providerName;
    private String testDate;
    private String testTime;
    private String testPicUrl;
    private String description;

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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestPicUrl() {
        return testPicUrl;
    }

    public void setTestPicUrl(String testPicUrl) {
        this.testPicUrl = testPicUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
