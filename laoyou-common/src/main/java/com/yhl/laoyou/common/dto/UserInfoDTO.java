package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import jxl.write.DateTime;

public class UserInfoDTO {

    //手机号
    @JSONField(name = "phoneNum")
    private String phoneNum;

    //验证码
    @JSONField(name = "IdentifyNum")
    private String IdentifyNum;

    @JSONField(name = "userID")
    private String userID;

    //来源
    @JSONField(name = "source")
    private String source;

    @JSONField(name = "headImageURL")
    private String headImageURL;

    @JSONField(name = "easemobID")
    private String easemobID;

    @JSONField(name = "easemobPassword")
    private String easemobPassword;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdentifyNum() {
        return IdentifyNum;
    }

    public void setIdentifyNum(String identifyNum) {
        IdentifyNum = identifyNum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHeadImageURL() {
        return headImageURL;
    }

    public void setHeadImageURL(String headImageURL) {
        this.headImageURL = headImageURL;
    }

    public String getEasemobID() {
        return easemobID;
    }

    public void setEasemobID(String easemobID) {
        this.easemobID = easemobID;
    }

    public String getEasemobPassword() {
        return easemobPassword;
    }

    public void setEasemobPassword(String easemobPassword) {
        this.easemobPassword = easemobPassword;
    }
}