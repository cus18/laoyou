package com.yhl.laoyou.modules.sys.entity;


import java.util.Date;
import java.util.List;

/**
 * Created by zbm84 on 2017/5/8.
 */
public class EasemobGroup {

  private String groupName;
  private String easemobGroupID;
  private Date   create_date;
  private String owner;
  private String elderEasemobID;
  private String doctorIDArray;
  private String nurse;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEasemobGroupID() {
        return easemobGroupID;
    }

    public void setEasemobGroupID(String easemobGroupID) {
        this.easemobGroupID = easemobGroupID;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getElderEasemobID() {
        return elderEasemobID;
    }

    public void setElderEasemobID(String elderEasemobID) {
        this.elderEasemobID = elderEasemobID;
    }

    public String getDoctorIDArray() {
        return doctorIDArray;
    }

    public void setDoctorIDArray(String doctorIDArray) {
        this.doctorIDArray = doctorIDArray;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

}
