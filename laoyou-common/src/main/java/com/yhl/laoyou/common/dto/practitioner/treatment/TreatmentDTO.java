package com.yhl.laoyou.common.dto.practitioner.treatment;

import com.alibaba.fastjson.annotation.JSONField;

public class TreatmentDTO {

    @JSONField(name = "updateDate")
    private String updateDate;

    @JSONField(name = "treatmentId")
    private String treatmentId;

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "elderName")
    private String elderName;

    @JSONField(name = "providerId")
    private String providerId;

    @JSONField(name = "providerName")
    private String providerName;

    @JSONField(name = "treatmentData")
    private String treatmentData;

    @JSONField(name = "treatmentAudio")
    private String treatmentAudio;

    @JSONField(name = "description")
    private String description;

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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

    public String getTreatmentData() {
        return treatmentData;
    }

    public String getTreatmentAudio() {
        return treatmentAudio;
    }

    public void setTreatmentAudio(String treatmentAudio) {
        this.treatmentAudio = treatmentAudio;
    }

    public void setTreatmentData(String treatmentData) {
        this.treatmentData = treatmentData;
    }
}