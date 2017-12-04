package com.yhl.laoyou.common.dto.practitioner.Settings;

import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by zbm84 on 2017/6/22.
 */
public class SysHospitalUserDTO extends DataEntity<SysHospitalUserDTO> implements java.io.Serializable {

    private static final long serialVersionUID = -3649249427795059809L;

    private String id;

    private String sysUserID;

    private String officeName;

    private String sysOfficeID;

    private String sysRoleID;

    private String phone;

    private String name;

    private String gender;

    private String photo;

    private Date createDate;

    private Date updateDate;

    private String loginToken;

    private String easemobID;

    private String easemobPassword;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserID() {
        return sysUserID;
    }

    public void setSysUserID(String sysUserID) {
        this.sysUserID = sysUserID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSysOfficeID() {
        return sysOfficeID;
    }

    public void setSysOfficeID(String sysOfficeID) {
        this.sysOfficeID = sysOfficeID;
    }

    public String getSysRoleID() {
        return sysRoleID;
    }

    public void setSysRoleID(String sysRoleID) {
        this.sysRoleID = sysRoleID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
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
}
