package com.yhl.laoyou.common.dto.practitioner.healthServicePackage;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FirstPartyDTO {

    @JSONField(name = "firstPartyName")
    private String firstPartyName;

    @JSONField(name = "firstPartySignature")
    private String firstPartySignature;

    @JSONField(name = "firstPartyPhone")
    private String firstPartyPhone;

    @JSONField(name = "doctorName")
    private String doctorName;

    @JSONField(name = "doctorSignature")
    private String doctorSignature;

    @JSONField(name = "doctorPhone")
    private String doctorPhone;

    @JSONField(name = "nurseName")
    private String nurseName;

    @JSONField(name = "nurseSignature")
    private String nurseSignature;

    @JSONField(name = "nursePhone")
    private String nursePhone;

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getFirstPartySignature() {
        return firstPartySignature;
    }

    public void setFirstPartySignature(String firstPartySignature) {
        this.firstPartySignature = firstPartySignature;
    }

    public String getFirstPartyPhone() {
        return firstPartyPhone;
    }

    public void setFirstPartyPhone(String firstPartyPhone) {
        this.firstPartyPhone = firstPartyPhone;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSignature() {
        return doctorSignature;
    }

    public void setDoctorSignature(String doctorSignature) {
        this.doctorSignature = doctorSignature;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseSignature() {
        return nurseSignature;
    }

    public void setNurseSignature(String nurseSignature) {
        this.nurseSignature = nurseSignature;
    }

    public String getNursePhone() {
        return nursePhone;
    }

    public void setNursePhone(String nursePhone) {
        this.nursePhone = nursePhone;
    }
}