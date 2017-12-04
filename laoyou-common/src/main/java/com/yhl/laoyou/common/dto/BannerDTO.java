package com.yhl.laoyou.common.dto;

import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/8/3.
 */
public class BannerDTO extends DataEntity<BannerDTO>{

    private String id;
    private String name; //活动名称
    private String introduce; //活动简单介绍
    private String banner; //banner 地址
    private String uri;   //点击跳转地址
    private Integer height;
    private Integer width;
    private String bannerResourceID;  //申请位置
    private String app;
    private String type; //资源位
    private String weights; //权重
    private String status; //状态
    private Date startDate;  //上架时间
    private Date endDate;  //下架时间


    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getBannerResourceID() {
        return bannerResourceID;
    }

    public void setBannerResourceID(String bannerResourceID) {
        this.bannerResourceID = bannerResourceID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
