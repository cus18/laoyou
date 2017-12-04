package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/15.
 */
public class BloodSugarData extends BaseAtr {
    private String bgValue;
    private String mealType;
    private String bgType;
    private String temperature;
    private String hba1c;
    private String period;


    public String getBgValue() {
        return bgValue;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public String getMealType() {
        return mealType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getBgType() {
        return bgType;
    }

    public void setBgType(String bgType) {
        this.bgType = bgType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHba1c() {
        return hba1c;
    }

    public void setHba1c(String hba1c) {
        this.hba1c = hba1c;
    }


}
