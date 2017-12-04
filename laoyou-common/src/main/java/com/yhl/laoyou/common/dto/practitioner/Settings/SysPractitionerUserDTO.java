package com.yhl.laoyou.common.dto.practitioner.Settings;


import java.util.Date;

/**
 * Created by zbm84 on 2017/5/9.
 */
public class SysPractitionerUserDTO implements java.io.Serializable{

    private static final long serialVersionUID = -3649249427795059809L;

    private String id;
    private String sysUserID;
    private String hospitalName;
    private String department;
    private String title;
    private String easemobID;
    private String easemobPassword;
    private Date   createDate;
    private Date   updateDate;
    private String loginToken;
    private String type;
    private String sysHospitalID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserID() {
        return sysUserID;
    }

    public void setSysUserID(String sysUserID) {
        this.sysUserID = sysUserID;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEasemobID() {
        return easemobID;
    }

    public void setEasemobID(String easemobID) {
        this.easemobID = easemobID;
    }

    public String getEasemobPassword() {
        return easemobPassword;
    }

    public void setEasemobPassword(String easemobPassword) {
        this.easemobPassword = easemobPassword;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getSysHospitalID() {
        return sysHospitalID;
    }

    public void setSysHospitalID(String sysHospitalID) {
        this.sysHospitalID = sysHospitalID;
    }
}
