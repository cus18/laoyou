package com.yhl.laoyou.common.dto.practitioner;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.dto.UserInfoDTO;

public class PractitionerInfoDTO {

    //手机号
    @JSONField(name = "practitionerId")
    private String practitionerId;

    //手机号
    @JSONField(name = "practitionerName")
    private String practitionerName;

    //手机号
    @JSONField(name = "userInfoDTO")
    private UserInfoDTO userInfoDTO;

    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getPractitionerName() {
        return practitionerName;
    }

    public void setPractitionerName(String practitionerName) {
        this.practitionerName = practitionerName;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }
}