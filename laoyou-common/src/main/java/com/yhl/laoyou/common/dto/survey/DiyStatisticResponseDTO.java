package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DiyStatisticResponseDTO {

    @JSONField(name = "diyStatisticResponseName")
    private String diyStatisticResponseName;

    @JSONField(name = "diyStatisticResponseValue")
    private Integer diyStatisticResponseValue;

    public String getDiyStatisticResponseName() {
        return diyStatisticResponseName;
    }

    public void setDiyStatisticResponseName(String diyStatisticResponseName) {
        this.diyStatisticResponseName = diyStatisticResponseName;
    }

    public Integer getDiyStatisticResponseValue() {
        return diyStatisticResponseValue;
    }

    public void setDiyStatisticResponseValue(Integer diyStatisticResponseValue) {
        this.diyStatisticResponseValue = diyStatisticResponseValue;
    }
}