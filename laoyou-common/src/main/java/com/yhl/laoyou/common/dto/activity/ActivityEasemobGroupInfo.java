package com.yhl.laoyou.common.dto.activity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zbm84 on 2017/8/10.
 */
public class ActivityEasemobGroupInfo {

    @JSONField(name = "userName")
    private String userName;

    @JSONField(name = "userPhoto")
    private String userPhoto;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
