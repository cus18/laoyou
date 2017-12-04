package com.yhl.laoyou.common.dto.elder;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.dto.UserInfoDTO;

public class ElderInfoDTO {

    //手机号
    @JSONField(name = "elderId")
    private String elderId;

    //手机号
    @JSONField(name = "elderName")
    private String elderName;

    //手机号
    @JSONField(name = "elderArchiveNum")
    private String elderArchiveNum;

    //手机号
    @JSONField(name = "userInfoDTO")
    private UserInfoDTO userInfoDTO;

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderArchiveNum() {
        return elderArchiveNum;
    }

    public void setElderArchiveNum(String elderArchiveNum) {
        this.elderArchiveNum = elderArchiveNum;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }
}