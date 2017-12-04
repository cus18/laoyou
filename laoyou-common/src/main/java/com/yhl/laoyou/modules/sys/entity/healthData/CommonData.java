package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/22.
 */
public class CommonData {

    private String diastolic;
    private String heartRate;
    private String systolic;
    private String measureTime;
    private String bgValue;
    private String mealType;
    private String remarks;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBgValue() {
        return bgValue;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }
}
