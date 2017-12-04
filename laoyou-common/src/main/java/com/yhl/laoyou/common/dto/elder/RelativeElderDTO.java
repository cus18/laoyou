package com.yhl.laoyou.common.dto.elder;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.dto.UserInfoDTO;

public class RelativeElderDTO {

    @JSONField(name = "elderID")
    private String elderID;

    //姓名
    @JSONField(name = "elderName")
    private String elderName;

    //性别
    @JSONField(name = "gender")
    private String gender;

    //年龄
    @JSONField(name = "age")
    private String age;

    //身份证号
    @JSONField(name = "idCard")
    private String idCard;

    //手机号
    @JSONField(name = "phone")
    private String phone;

    //头像
    @JSONField(name = "headImage")
    private String headImage;

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getElderID() {
        return elderID;
    }

    public void setElderID(String elderID) {
        this.elderID = elderID;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}