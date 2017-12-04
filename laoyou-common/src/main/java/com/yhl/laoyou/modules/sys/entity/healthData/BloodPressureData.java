package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/15.
 */
public class BloodPressureData extends BaseAtr {
    private String bpResult;
    private String diastolicResult;
    private String diastolic;
    private String heartRate;
    private String hrResult;
    private String meantolic;
    private String systolicResult;
    private String systolic;


    public String getBpResult() {
        return bpResult;
    }

    public void setBpResult(String bpResult) {
        this.bpResult = bpResult;
    }

    public String getDiastolicResult() {
        return diastolicResult;
    }

    public void setDiastolicResult(String diastolicResult) {
        this.diastolicResult = diastolicResult;
    }

    public String getDiastolic() {
        return diastolic;
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

    public String getHrResult() {
        return hrResult;
    }

    public void setHrResult(String hrResult) {
        this.hrResult = hrResult;
    }

    public String getMeantolic() {
        return meantolic;
    }

    public void setMeantolic(String meantolic) {
        this.meantolic = meantolic;
    }

    public String getSystolicResult() {
        return systolicResult;
    }

    public void setSystolicResult(String systolicResult) {
        this.systolicResult = systolicResult;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }
}
