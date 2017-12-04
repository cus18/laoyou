package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SingleStatisticDTO {

    @JSONField(name = "singleStatisticName")
    private String singleStatisticName;

    @JSONField(name = "singleStatisticNum")
    private String singleStatisticNum;

    @JSONField(name = "singleStatisticDTOList")
    private List<SingleStatisticDTO> singleStatisticDTOList;

    public String getSingleStatisticName() {
        return singleStatisticName;
    }

    public void setSingleStatisticName(String singleStatisticName) {
        this.singleStatisticName = singleStatisticName;
    }

    public String getSingleStatisticNum() {
        return singleStatisticNum;
    }

    public void setSingleStatisticNum(String singleStatisticNum) {
        this.singleStatisticNum = singleStatisticNum;
    }


    public List<SingleStatisticDTO> getSingleStatisticDTOList() {
        return singleStatisticDTOList;
    }

    public void setSingleStatisticDTOList(List<SingleStatisticDTO> singleStatisticDTOList) {
        this.singleStatisticDTOList = singleStatisticDTOList;
    }
}