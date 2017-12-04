package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

public class QuestionItemDTO {

    //题目标号
    @JSONField(name = "questionItem")
    private String questionItem;

    //题目名称
    @JSONField(name = "questionItemName")
    private String questionItemName;

    public String getQuestionItem() {
        return questionItem;
    }

    public void setQuestionItem(String questionItem) {
        this.questionItem = questionItem;
    }

    public String getQuestionItemName() {
        return questionItemName;
    }

    public void setQuestionItemName(String questionItemName) {
        this.questionItemName = questionItemName;
    }
}