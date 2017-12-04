package com.yhl.laoyou.common.dto.practitioner.talk;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TalkMessageListDTO {

    @JSONField(name = "groupId")
    private String groupId;

    @JSONField(name = "groupName")
    private String groupName;

    @JSONField(name = "talkMessageList")
    private List<TalkMessageDTO> talkMessageDtoList;

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

    public List<TalkMessageDTO> getTalkMessageDtoList() {
        return talkMessageDtoList;
    }

    public void setTalkMessageDtoList(List<TalkMessageDTO> talkMessageDtoList) {
        this.talkMessageDtoList = talkMessageDtoList;
    }
}