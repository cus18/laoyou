package com.yhl.laoyou.modules.sys.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/5/19.
 */
public class EasemobChatMessage {

    private String id;
    private Date sendTime;
    private String userEasemobID;
    private String toEasemobID;
    private String chatType;
    private String message;
    private String audioLength;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getUserEasemobID() {
        return userEasemobID;
    }

    public void setUserEasemobID(String userEasemobID) {
        this.userEasemobID = userEasemobID;
    }

    public String getToEasemobID() {
        return toEasemobID;
    }

    public void setToEasemobID(String toEasemobID) {
        this.toEasemobID = toEasemobID;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAudioLength() {
        return audioLength;
    }

    public void setAudioLength(String audioLength) {
        this.audioLength = audioLength;
    }
}
