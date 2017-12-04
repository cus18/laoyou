package com.yhl.laoyou.common.dto.practitioner.healthServicePackage;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SecondPartyDTO {

    @JSONField(name = "secondPartyName")
    private String secondPartyName;

    @JSONField(name = "secondPartySignature")
    private String secondPartySignature;

    @JSONField(name = "idNum")
    private String idNum;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "mobile")
    private String mobile;

    @JSONField(name = "telephone")
    private String telephone;

    @JSONField(name = "emergencyName")
    private String emergencyName;

    @JSONField(name = "emergencyPhone")
    private String emergencyPhone;

    public String getSecondPartyName() {
        return secondPartyName;
    }

    public void setSecondPartyName(String secondPartyName) {
        this.secondPartyName = secondPartyName;
    }

    public String getSecondPartySignature() {
        return secondPartySignature;
    }

    public void setSecondPartySignature(String secondPartySignature) {
        this.secondPartySignature = secondPartySignature;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
}