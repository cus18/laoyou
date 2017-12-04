package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/15.
 */
public class BloodOxygenData extends BaseAtr{


    private String spo2;
    private String heartRate;

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }
}
