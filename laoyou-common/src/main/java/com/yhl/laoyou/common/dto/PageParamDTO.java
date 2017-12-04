package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class PageParamDTO<T> {

    @JSONField(name = "pageNo")
    private String pageNo; //当前第几页

    @JSONField(name = "pageSize")
    private String pageSize; //每页的条目数

    /**按参数排序的类型
     *  0，群聊天的消息数
     *  1，记录创建的时间日期
     */
    @JSONField(name = "orderType")
    private String orderType;

    @JSONField(name = "orderBy")
    private String orderBy;  //按参数排序，0位由高到底，1为由低到高

    @JSONField(name = "requestData")
    private T requestData;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}