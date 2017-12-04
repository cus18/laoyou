package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SurveyDTO {

    @JSONField(name = "_id")
    private String _id;

    //题目ID号
    @JSONField(name = "questionId")
    private Integer questionId;

    //题目名称
    @JSONField(name = "questionName")
    private String questionName;

    //题目类型
    /**
     * single 单选
     * multi 多选
     * input 填空
     *
     * */
    @JSONField(name = "questionType")
    private String questionType;

    //题目的模版
    @JSONField(name = "questionTemp")
    private String questionTemp;

    //题目的模版
    @JSONField(name = "questionStatus")
    private String questionStatus;

    //来源
    @JSONField(name = "questionData")
    private List<QuestionItemDTO> questionData;

    //来源
    @JSONField(name = "mustAnswer")
    private Boolean mustAnswer;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTemp() {
        return questionTemp;
    }

    public void setQuestionTemp(String questionTemp) {
        this.questionTemp = questionTemp;
    }

    public List<QuestionItemDTO> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<QuestionItemDTO> questionData) {
        this.questionData = questionData;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Boolean getMustAnswer() {
        return mustAnswer;
    }

    public void setMustAnswer(Boolean mustAnswer) {
        this.mustAnswer = mustAnswer;
    }
}