package com.yhl.laoyou.modules.notificationRemindService.entity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/8/16.
 */
public class NotificationEntity {

  private String id;
  private String message_temlate_id;
  private String title;
  private String content;
  private String uri;
  private Date   create_date;
  private Date   update_date;
  private String create_by;

  private String sys_elder_user_id;
  private String notification_user_id;
  private String status;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage_temlate_id() {
    return message_temlate_id;
  }

  public void setMessage_temlate_id(String message_temlate_id) {
    this.message_temlate_id = message_temlate_id;
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

  public String getSys_elder_user_id() {
    return sys_elder_user_id;
  }

  public void setSys_elder_user_id(String sys_elder_user_id) {
    this.sys_elder_user_id = sys_elder_user_id;
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

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getNotification_user_id() {
    return notification_user_id;
  }

  public void setNotification_user_id(String notification_user_id) {
    this.notification_user_id = notification_user_id;
  }
}
