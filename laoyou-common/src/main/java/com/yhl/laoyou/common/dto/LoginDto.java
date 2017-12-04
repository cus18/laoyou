package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zbm84 on 2017/5/24.
 */
public class LoginDto {

    @JSONField(name = "loginToken")
    private String loginToken;

    @JSONField(name = "easemobID")
    private String easemobID;

    @JSONField(name = "easemobPassword")
    private String easemobPassword;

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
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
