package com.yhl.laoyou.modules.notificationRemindService.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/8/16.
 */
public class RemindEntity {

  private String id;
  private String remind_temlate_id;
  private String title;
  private String content;
  private String uri;
  private String icon;
  private String remark;
  private Date   create_date;
  private Date   update_date;
  private String create_by;
  private String type;
  private String type_id;

  private String sys_elder_user_id;
  private String remind_user_id;
  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRemind_temlate_id() {
    return remind_temlate_id;
  }

  public void setRemind_temlate_id(String remind_temlate_id) {
    this.remind_temlate_id = remind_temlate_id;
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getSys_elder_user_id() {
    return sys_elder_user_id;
  }

  public void setSys_elder_user_id(String sys_elder_user_id) {
    this.sys_elder_user_id = sys_elder_user_id;
  }

  public String getRemind_user_id() {
    return remind_user_id;
  }

  public void setRemind_user_id(String remind_user_id) {
    this.remind_user_id = remind_user_id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType_id() {
    return type_id;
  }

  public void setType_id(String type_id) {
    this.type_id = type_id;
  }
}
