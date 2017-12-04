package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class AnswerDTO {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "questionId")
    private Integer questionId;

    @JSONField(name = "questionName")
    private String questionName;

    @JSONField(name = "workerName")
    private String workerName;

    @JSONField(name = "workerPhone")
    private String workerPhone;

    @JSONField(name = "questionAnswer")
    private String questionAnswer;

    @JSONField(name = "updateDate")
    private Date updateDate;

    @JSONField(name = "elderIdentityNum")
    private String elderIdentityNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getElderIdentityNum() {
        return elderIdentityNum;
    }

    public void setElderIdentityNum(String elderIdentityNum) {
        this.elderIdentityNum = elderIdentityNum;
    }
}