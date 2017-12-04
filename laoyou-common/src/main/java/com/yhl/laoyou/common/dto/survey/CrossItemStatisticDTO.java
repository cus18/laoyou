package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

public class CrossItemStatisticDTO {

    @JSONField(name = "keyName")
    private String keyName;

    @JSONField(name = "keyValue")
    private Integer keyValue;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Integer getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Integer keyValue) {
        this.keyValue = keyValue;
    }
}