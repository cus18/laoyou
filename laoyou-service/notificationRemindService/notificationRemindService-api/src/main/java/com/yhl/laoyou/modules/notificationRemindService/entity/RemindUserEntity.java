package com.yhl.laoyou.modules.notificationRemindService.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/8/17.
 */
public class RemindUserEntity {

    private String id;
    private String sys_elder_user_id;
    private String remind_id;
    private String status;
    private Date   create_date;
    private Date   update_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSys_elder_user_id() {
        return sys_elder_user_id;
    }

    public void setSys_elder_user_id(String sys_elder_user_id) {
        this.sys_elder_user_id = sys_elder_user_id;
    }

    public String getRemind_id() {
        return remind_id;
    }

    public void setRemind_id(String remind_id) {
        this.remind_id = remind_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
