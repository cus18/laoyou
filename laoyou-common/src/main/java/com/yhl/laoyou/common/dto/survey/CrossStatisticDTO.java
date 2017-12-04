package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class CrossStatisticDTO {

    @JSONField(name = "crossItemStatisticDTOList")
    private List<CrossItemStatisticDTO> crossItemStatisticDTOList;

    public List<CrossItemStatisticDTO> getCrossItemStatisticDTOList() {
        return crossItemStatisticDTOList;
    }

    public void setCrossItemStatisticDTOList(List<CrossItemStatisticDTO> crossItemStatisticDTOList) {
        this.crossItemStatisticDTOList = crossItemStatisticDTOList;
    }
}