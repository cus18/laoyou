package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FrontAnswerDTO {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "question_id")
    private String question_id;

    @JSONField(name = "question_name")
    private String question_name;

    @JSONField(name = "worker_name")
    private String worker_name;

    @JSONField(name = "worker_phone")
    private String worker_phone;

    @JSONField(name = "question_answer")
    private String question_answer;

    @JSONField(name = "update_date")
    private Date update_date;

    @JSONField(name = "elder_identity_num")
    private String elder_identity_num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_phone() {
        return worker_phone;
    }

    public void setWorker_phone(String worker_phone) {
        this.worker_phone = worker_phone;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getElder_identity_num() {
        return elder_identity_num;
    }

    public void setElder_identity_num(String elder_identity_num) {
        this.elder_identity_num = elder_identity_num;
    }
}