package com.yhl.laoyou.common.dto.practitioner.talk;

import com.alibaba.fastjson.annotation.JSONField;

public class TalkMessageDTO {

    @JSONField(name = "messageId")
    private String messageId;

    @JSONField(name = "dateTime")
    private String dateTime;

    @JSONField(name = "messageType")
    private String messageType;

    @JSONField(name = "messageContent")
    private String messageContent;

    @JSONField(name = "messageUnRead")
    private Boolean messageUnRead;

    @JSONField(name = "length")
    private String length;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "filename")
    private String filename;

    @JSONField(name = "senderId")
    private String senderId;

    @JSONField(name = "senderName")
    private String senderName;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Boolean getMessageUnRead() {
        return messageUnRead;
    }

    public void setMessageUnRead(Boolean messageUnRead) {
        this.messageUnRead = messageUnRead;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}