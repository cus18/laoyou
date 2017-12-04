package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

public class DiyStatisticRequestDTO {

    @JSONField(name = "surveyDTO")
    private SurveyDTO surveyDTO;

    //condition为以下几种条件
    /***
     *
     * equal
     * larger
     * smaller
     * notEqual
     * largerAndEqual
     * smallerAndEqual
     *
     * **/
    @JSONField(name = "condition")
    private String condition;

    @JSONField(name = "statisticValue")
    private String statisticValue;

    public SurveyDTO getSurveyDTO() {
        return surveyDTO;
    }

    public void setSurveyDTO(SurveyDTO surveyDTO) {
        this.surveyDTO = surveyDTO;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatisticValue() {
        return statisticValue;
    }

    public void setStatisticValue(String statisticValue) {
        this.statisticValue = statisticValue;
    }
}
