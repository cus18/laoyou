package com.yhl.laoyou.modules.notificationRemindService.entity;

import com.yhl.laoyou.common.persistence.DataEntity;

/**
 * Created by zbm84 on 2017/8/16.
 */
public class NotificationTemplateEntity extends DataEntity {

    private String id;
    private String title;
    private String content;
    private String uri;
    private String create_by;
    private String create_date;
    private String update_date;
    private String del_flage;


    private Integer pageNo;
    private Integer pageSize;
    private String startUpdateDate;
    private String endUpdateDate;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartUpdateDate() {
        return startUpdateDate;
    }

    public void setStartUpdateDate(String startUpdateDate) {
        this.startUpdateDate = startUpdateDate;
    }

    public String getEndUpdateDate() {
        return endUpdateDate;
    }

    public void setEndUpdateDate(String endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getDel_flage() {
        return del_flage;
    }

    public void setDel_flage(String del_flage) {
        this.del_flage = del_flage;
    }
}
