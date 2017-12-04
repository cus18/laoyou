package com.yhl.laoyou.common.dto;

import com.yhl.laoyou.common.utils.excel.ExcelField;

/**
 * Created by sunxiao on 2017/7/7.
 */
public class EmptyNestElderBaseRogerInfo {

//    @ExcelField(title = "ID 问卷编号")
//    private String idNum;

    @ExcelField(title="街道名")
    private String street;

    @ExcelField(title="社区名")
    private String community;

    @ExcelField(title="姓名")
    private String name;

    @ExcelField(title="身份证号码")
    private String idCard;

    @ExcelField(title="年龄")
    private String age;

    @ExcelField(title="性别")
    private String gender;

    @ExcelField(title="手机号码")
    private String mobile;

    @ExcelField(title="固定电话")
    private String phone;

    @ExcelField(title="名族")
    private String nation;

    @ExcelField(title="籍贯")
    private String origin;

    @ExcelField(title="政治面貌")
    private String political;

    @ExcelField(title="文化程度")
    private String cultural;

    @ExcelField(title="婚姻情况")
    private String marital;

    @ExcelField(title="身份情况")
    private String identity;

    @ExcelField(title="户口类型")
    private String householdType;

    @ExcelField(title="户籍状态")
    private String censusStatus;

    @ExcelField(title="居住情况")
    private String livingConditions;

    @ExcelField(title="户籍地址")
    private String permanentAddress;

    @ExcelField(title="户籍所在地")
    private String permanentArea;

    @ExcelField(title="常住地址")
    private String livingAddress;

    @ExcelField(title="社区电话")
    private String communityPhone;

    @ExcelField(title="是否残疾")
    private String disability;

    @ExcelField(title="残疾证")
    private String disabilityCard;

    @ExcelField(title="亲人一")
    private String relativesName1;

    @ExcelField(title="亲人一关系")
    private String relativesRelationship1;

    @ExcelField(title="亲人一联系电话")
    private String relativesPhone1;

    @ExcelField(title="亲人一住址")
    private String relativesAddress1;

    @ExcelField(title="亲人一所在区")
    private String relativesArea1;

    @ExcelField(title="亲人二")
    private String relativesName2;

    @ExcelField(title="亲人二关系")
    private String relativesRelationship2;

    @ExcelField(title="亲人二电话")
    private String relativesPhone2;

    @ExcelField(title="亲人二住址")
    private String relativesAddress2;

    @ExcelField(title="亲人二所在区")
    private String relativesArea2;

    @ExcelField(title="村干部")
    private String villageCadres;

    @ExcelField(title="村干部电话")
    private String villageCadresPhone;

    @ExcelField(title="村干部住址")
    private String villageCadresAddress;

    @ExcelField(title="党员志愿者")
    private String partyMemberVolunteers;

    @ExcelField(title="党员志愿者电话")
    private String partyMemberVolunteersPhone;

    @ExcelField(title="党员志愿者住址")
    private String partyMemberVolunteersAddress;

    @ExcelField(title="备注")
    private String remark;

    @ExcelField(title="填表人")
    private String formHolder;

    @ExcelField(title="审核人")
    private String auditor;

    public String getPermanentArea() {
        return permanentArea;
    }

    public void setPermanentArea(String permanentArea) {
        this.permanentArea = permanentArea;
    }

    public String getRelativesArea1() {
        return relativesArea1;
    }

    public void setRelativesArea1(String relativesArea1) {
        this.relativesArea1 = relativesArea1;
    }

    public String getRelativesArea2() {
        return relativesArea2;
    }

    public void setRelativesArea2(String relativesArea2) {
        this.relativesArea2 = relativesArea2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getCultural() {
        return cultural;
    }

    public void setCultural(String cultural) {
        this.cultural = cultural;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getCensusStatus() {
        return censusStatus;
    }

    public void setCensusStatus(String censusStatus) {
        this.censusStatus = censusStatus;
    }

    public String getLivingConditions() {
        return livingConditions;
    }

    public void setLivingConditions(String livingConditions) {
        this.livingConditions = livingConditions;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getCommunityPhone() {
        return communityPhone;
    }

    public void setCommunityPhone(String communityPhone) {
        this.communityPhone = communityPhone;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDisabilityCard() {
        return disabilityCard;
    }

    public void setDisabilityCard(String disabilityCard) {
        this.disabilityCard = disabilityCard;
    }

    public String getRelativesName1() {
        return relativesName1;
    }

    public void setRelativesName1(String relativesName1) {
        this.relativesName1 = relativesName1;
    }

    public String getRelativesRelationship1() {
        return relativesRelationship1;
    }

    public void setRelativesRelationship1(String relativesRelationship1) {
        this.relativesRelationship1 = relativesRelationship1;
    }

    public String getRelativesPhone1() {
        return relativesPhone1;
    }

    public void setRelativesPhone1(String relativesPhone1) {
        this.relativesPhone1 = relativesPhone1;
    }

    public String getRelativesAddress1() {
        return relativesAddress1;
    }

    public void setRelativesAddress1(String relativesAddress1) {
        this.relativesAddress1 = relativesAddress1;
    }

    public String getRelativesName2() {
        return relativesName2;
    }

    public void setRelativesName2(String relativesName2) {
        this.relativesName2 = relativesName2;
    }

    public String getRelativesRelationship2() {
        return relativesRelationship2;
    }

    public void setRelativesRelationship2(String relativesRelationship2) {
        this.relativesRelationship2 = relativesRelationship2;
    }

    public String getRelativesPhone2() {
        return relativesPhone2;
    }

    public void setRelativesPhone2(String relativesPhone2) {
        this.relativesPhone2 = relativesPhone2;
    }

    public String getRelativesAddress2() {
        return relativesAddress2;
    }

    public void setRelativesAddress2(String relativesAddress2) {
        this.relativesAddress2 = relativesAddress2;
    }

    public String getVillageCadres() {
        return villageCadres;
    }

    public void setVillageCadres(String villageCadres) {
        this.villageCadres = villageCadres;
    }

    public String getVillageCadresPhone() {
        return villageCadresPhone;
    }

    public void setVillageCadresPhone(String villageCadresPhone) {
        this.villageCadresPhone = villageCadresPhone;
    }

    public String getVillageCadresAddress() {
        return villageCadresAddress;
    }

    public void setVillageCadresAddress(String villageCadresAddress) {
        this.villageCadresAddress = villageCadresAddress;
    }

    public String getPartyMemberVolunteers() {
        return partyMemberVolunteers;
    }

    public void setPartyMemberVolunteers(String partyMemberVolunteers) {
        this.partyMemberVolunteers = partyMemberVolunteers;
    }

    public String getPartyMemberVolunteersPhone() {
        return partyMemberVolunteersPhone;
    }

    public void setPartyMemberVolunteersPhone(String partyMemberVolunteersPhone) {
        this.partyMemberVolunteersPhone = partyMemberVolunteersPhone;
    }

    public String getPartyMemberVolunteersAddress() {
        return partyMemberVolunteersAddress;
    }

    public void setPartyMemberVolunteersAddress(String partyMemberVolunteersAddress) {
        this.partyMemberVolunteersAddress = partyMemberVolunteersAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFormHolder() {
        return formHolder;
    }

    public void setFormHolder(String formHolder) {
        this.formHolder = formHolder;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

//    public String getIdNum() {
//        return idNum;
//    }
//
//    public void setIdNum(String idNum) {
//        this.idNum = idNum;
//    }
}
