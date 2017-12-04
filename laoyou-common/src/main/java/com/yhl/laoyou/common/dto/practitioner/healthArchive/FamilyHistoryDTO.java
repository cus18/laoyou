package com.yhl.laoyou.common.dto.practitioner.healthArchive;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FamilyHistoryDTO {

    @JSONField(name = "father")
    private String father;

    @JSONField(name = "mother")
    private String mother;

    @JSONField(name = "brother")
    private String brother;

    @JSONField(name = "child")
    private String child;

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getBrother() {
        return brother;
    }

    public void setBrother(String brother) {
        this.brother = brother;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}