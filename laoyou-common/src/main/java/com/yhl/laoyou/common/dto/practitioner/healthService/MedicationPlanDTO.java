package com.yhl.laoyou.common.dto.practitioner.healthService;

import com.alibaba.fastjson.annotation.JSONField;
import flexjson.JSON;

import java.util.Date;

/**
 * Created by zbm84 on 2017/5/25.
 */
public class MedicationPlanDTO {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "drugName")
    private String drugName;

    @JSONField(name = "dose")
    private String dose;

    @JSONField(name = "usageTime")
    private String usageTime;

    @JSONField(name = "repeat")
    private String repeat;


    @JSONField(name = "startTime")
    private String startTime;

    @JSONField(name = "endTime")
    private String endTime;

    @JSONField(name = "remark")
    private String remark;

    @JSONField(name = "timeLine")
    private String timeLine;

    @JSONField(name = "minuteLine")
    private String minuteLine;

    @JSONField(name = "status")
    private String status;

    private Date createDate;

    private Date updateDate;

    private String completeStatus;

    private String elderID;

    @JSONField(name = "medicationPlanTiming")
    private MedicationPlanTimingDTO medicationPlanTimingDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getElderID() {
        return elderID;
    }

    public void setElderID(String elderID) {
        this.elderID = elderID;
    }

    public MedicationPlanTimingDTO getMedicationPlanTimingDTO() {
        return medicationPlanTimingDTO;
    }

    public void setMedicationPlanTimingDTO(MedicationPlanTimingDTO medicationPlanTimingDTO) {
        this.medicationPlanTimingDTO = medicationPlanTimingDTO;
    }

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }

    public String getMinuteLine() {
        return minuteLine;
    }

    public void setMinuteLine(String minuteLine) {
        this.minuteLine = minuteLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
