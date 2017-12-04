package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/22.
 */
public class ControlTargetData {

    private String elderId;
    private String elderName;
    private String type;
    private String fasting;
    private String beforeMeal;
    private String afterMeal;
    private String beforeBed;
    private String dawn;
    private String heartRate;
    private String diastolic;
    private String systolic;

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getFasting() {
        return fasting;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public void setFasting(String fasting) {
        this.fasting = fasting;
    }

    public String getBeforeMeal() {
        return beforeMeal;
    }

    public void setBeforeMeal(String beforeMeal) {
        this.beforeMeal = beforeMeal;
    }

    public String getAfterMeal() {
        return afterMeal;
    }

    public void setAfterMeal(String afterMeal) {
        this.afterMeal = afterMeal;
    }

    public String getBeforeBed() {
        return beforeBed;
    }

    public void setBeforeBed(String beforeBed) {
        this.beforeBed = beforeBed;
    }

    public String getDawn() {
        return dawn;
    }

    public void setDawn(String dawn) {
        this.dawn = dawn;
    }
}
