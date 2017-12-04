package com.yhl.laoyou.common.dto.practitioner.member;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberDTO {

    @JSONField(name = "elderId")
    private String elderId;

    @JSONField(name = "memberName")
    private String memberName;

    @JSONField(name = "age")
    private String age;

    @JSONField(name = "memberIcon")
    private String memberIcon;

    @JSONField(name = "archivesNum")
    private String archivesNum;

    @JSONField(name = "memberExtendData")
    private String memberExtendData;

    @JSONField(name = "memberCardID")
    private String memberCardID;

    @JSONField(name = "doctorName")
    private String doctorName;

    @JSONField(name = "nurseName")
    private String nurseName;

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMemberIcon() {
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public String getArchivesNum() {
        return archivesNum;
    }

    public void setArchivesNum(String archivesNum) {
        this.archivesNum = archivesNum;
    }

    public String getMemberExtendData() {
        return memberExtendData;
    }

    public void setMemberExtendData(String memberExtendData) {
        this.memberExtendData = memberExtendData;
    }

    public String getMemberCardID() {
        return memberCardID;
    }

    public void setMemberCardID(String memberCardID) {
        this.memberCardID = memberCardID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }
}