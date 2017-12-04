package com.yhl.laoyou.common.dto.practitioner.talk;

import com.alibaba.fastjson.annotation.JSONField;
import jxl.write.DateTime;

public class GroupTalkDTO {

    @JSONField(name = "groupId")
    private String groupId;

    @JSONField(name = "groupName")
    private String groupName;

    @JSONField(name = "unReadMessageNum")
    private String unReadMessageNum;

    @JSONField(name = "lastMessageType")
    private String lastMessageType;

    @JSONField(name = "lastMessageTime")
    private DateTime lastMessageTime;

    @JSONField(name = "elderName")
    private String elderName;

    @JSONField(name = "noDisturb")
    private Boolean noDisturb;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUnReadMessageNum() {
        return unReadMessageNum;
    }

    public void setUnReadMessageNum(String unReadMessageNum) {
        this.unReadMessageNum = unReadMessageNum;
    }

    public String getLastMessageType() {
        return lastMessageType;
    }

    public void setLastMessageType(String lastMessageType) {
        this.lastMessageType = lastMessageType;
    }

    public DateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(DateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public Boolean getNoDisturb() {
        return noDisturb;
    }

    public void setNoDisturb(Boolean noDisturb) {
        this.noDisturb = noDisturb;
    }
}