package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/6/1.
 */
public class SportData extends BaseAtr{

    private String stepCount;
    private String stepLength;
    private String consumeHeat;
    private String burnFat;
    private String weight;

    public String getStepCount() {
        return stepCount;
    }

    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getStepLength() {
        return stepLength;
    }

    public void setStepLength(String stepLength) {
        this.stepLength = stepLength;
    }

    public String getConsumeHeat() {
        return consumeHeat;
    }

    public void setConsumeHeat(String consumeHeat) {
        this.consumeHeat = consumeHeat;
    }

    public String getBurnFat() {
        return burnFat;
    }

    public void setBurnFat(String burnFat) {
        this.burnFat = burnFat;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
