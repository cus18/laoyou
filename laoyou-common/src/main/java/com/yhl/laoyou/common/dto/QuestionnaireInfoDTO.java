package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.utils.excel.ExcelField;

import java.util.Date;

public class QuestionnaireInfoDTO {


    private String id;

    @ExcelField(title = "姓名")
    private String name;

    @ExcelField(title = "年龄")
    private String age;

    @ExcelField(title = "身份证号码")
    private String idCard;

    @ExcelField(title = "原职业情况")
    private String lastJob;

    @ExcelField(title = "原职业情况E")
    private String lastJobE;

    @ExcelField(title = "您目前的经济来源情况？A")
    private String moneySourceA;

    @ExcelField(title = "您目前的经济来源情况？B")
    private String moneySourceB;

    @ExcelField(title = "您目前的经济来源情况？C")
    private String moneySourceC;

    @ExcelField(title = "是否是中共党员")
    private String ifPartyMembers;

    @ExcelField(title = "您需要党组织提供什么服务？")
    private String needPartyServices;

    @ExcelField(title = "您需要党组织提供什么服务？E")
    private String needPartyServicesE;

    @ExcelField(title = "您患慢性病情况")
    private String sickness;

    @ExcelField(title = "您患慢性病情况F")
    private String sicknessF;

    @ExcelField(title = "是否需要院前急救（120或999）？")
    private String needFirstAidServices;

    @ExcelField(title = "您希望我们今后为您提供哪些公共卫生和基本医疗服务？-卫生公共服务")
    private String needHealthServices;

    @ExcelField(title = "您希望我们今后为您提供哪些公共卫生和基本医疗服务？-基本医疗服务")
    private String needMedicalServices;

    @ExcelField(title = "您想要以何种方式养老？")
    private String whatPension;

    @ExcelField(title = "您想要以何种方式养老？E")
    private String whatPensionE;

    @ExcelField(title = "您需要什么样的服务？-生活服务")
    private String needLifeServices;

    @ExcelField(title = "您需要什么样的服务？-紧急救助服务")
    private String needEmergencyServices;

    @ExcelField(title = "您需要什么样的服务？-防走失定位服务")
    private String needGPSServices;

    @ExcelField(title = "是否有意愿配置智能养老呼叫设备？")
    private String needPhoneServices;

    @ExcelField(title = "如意愿配置智能养老呼叫设备，您认为需要设置哪些功能和服务？")
    private String whatNeedPhoneServices;

    @ExcelField(title = "调查人")
    private String investigator;

    private String inputerName;

    private String street;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastJob() {
        return lastJob;
    }

    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }

    public String getLastJobE() {
        return lastJobE;
    }

    public void setLastJobE(String lastJobE) {
        this.lastJobE = lastJobE;
    }

    public String getMoneySourceA() {
        return moneySourceA;
    }

    public void setMoneySourceA(String moneySourceA) {
        this.moneySourceA = moneySourceA;
    }

    public String getMoneySourceB() {
        return moneySourceB;
    }

    public void setMoneySourceB(String moneySourceB) {
        this.moneySourceB = moneySourceB;
    }

    public String getMoneySourceC() {
        return moneySourceC;
    }

    public void setMoneySourceC(String moneySourceC) {
        this.moneySourceC = moneySourceC;
    }

    public String getIfPartyMembers() {
        return ifPartyMembers;
    }

    public void setIfPartyMembers(String ifPartyMembers) {
        this.ifPartyMembers = ifPartyMembers;
    }

    public String getNeedPartyServices() {
        return needPartyServices;
    }

    public void setNeedPartyServices(String needPartyServices) {
        this.needPartyServices = needPartyServices;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getSicknessF() {
        return sicknessF;
    }

    public void setSicknessF(String sicknessF) {
        this.sicknessF = sicknessF;
    }

    public String getNeedFirstAidServices() {
        return needFirstAidServices;
    }

    public void setNeedFirstAidServices(String needFirstAidServices) {
        this.needFirstAidServices = needFirstAidServices;
    }

    public String getNeedHealthServices() {
        return needHealthServices;
    }

    public void setNeedHealthServices(String needHealthServices) {
        this.needHealthServices = needHealthServices;
    }

    public String getNeedMedicalServices() {
        return needMedicalServices;
    }

    public void setNeedMedicalServices(String needMedicalServices) {
        this.needMedicalServices = needMedicalServices;
    }

    public String getWhatPension() {
        return whatPension;
    }

    public void setWhatPension(String whatPension) {
        this.whatPension = whatPension;
    }

    public String getWhatPensionE() {
        return whatPensionE;
    }

    public void setWhatPensionE(String whatPensionE) {
        this.whatPensionE = whatPensionE;
    }

    public String getNeedLifeServices() {
        return needLifeServices;
    }

    public void setNeedLifeServices(String needLifeServices) {
        this.needLifeServices = needLifeServices;
    }

    public String getNeedEmergencyServices() {
        return needEmergencyServices;
    }

    public void setNeedEmergencyServices(String needEmergencyServices) {
        this.needEmergencyServices = needEmergencyServices;
    }

    public String getNeedGPSServices() {
        return needGPSServices;
    }

    public void setNeedGPSServices(String needGPSServices) {
        this.needGPSServices = needGPSServices;
    }

    public String getNeedPhoneServices() {
        return needPhoneServices;
    }

    public void setNeedPhoneServices(String needPhoneServices) {
        this.needPhoneServices = needPhoneServices;
    }

    public String getWhatNeedPhoneServices() {
        return whatNeedPhoneServices;
    }

    public void setWhatNeedPhoneServices(String whatNeedPhoneServices) {
        this.whatNeedPhoneServices = whatNeedPhoneServices;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNeedPartyServicesE() {
        return needPartyServicesE;
    }

    public void setNeedPartyServicesE(String needPartyServicesE) {
        this.needPartyServicesE = needPartyServicesE;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}