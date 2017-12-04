package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/15.
 */
public class MeasurementRule<T> {

    private String elderId;
    private String type;
    private T rule;

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getRule() {
        return rule;
    }

    public void setRule(T rule) {
        this.rule = rule;
    }
}
