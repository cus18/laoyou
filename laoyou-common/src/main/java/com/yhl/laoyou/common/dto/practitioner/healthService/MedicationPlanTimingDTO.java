package com.yhl.laoyou.common.dto.practitioner.healthService;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zbm84 on 2017/5/26.
 */
public class MedicationPlanTimingDTO {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "medicationPlanID")
    private String medicationPlanID;

    private Date  createDate;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "usageTime")
    private String usageTime;

    @JSONField(name = "drugName")
    private String drugName;

    @JSONField(name = "dose")
    private String dose;

    @JSONField(name = "doDate")
    private String usageDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicationPlanID() {
        return medicationPlanID;
    }

    public void setMedicationPlanID(String medicationPlanID) {
        this.medicationPlanID = medicationPlanID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(String usageDate) {
        this.usageDate = usageDate;
    }
}
