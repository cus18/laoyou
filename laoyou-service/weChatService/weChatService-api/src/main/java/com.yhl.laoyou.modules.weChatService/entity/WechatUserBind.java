package com.yhl.laoyou.modules.weChatService.entity;

import java.util.Date;

/**
 * Created by sunxiao on 2017/9/11.
 */
public class WechatUserBind {

    private Integer id;

    private String phone;

    private String idCard;

    private String openId;

    private Date createTime;

    private String batch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
