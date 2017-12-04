package com.yhl.laoyou.common.dto.practitioner.Settings;

import com.yhl.laoyou.common.persistence.DataEntity;
import com.yhl.laoyou.common.persistence.Page;

/**
 * Created by zbm84 on 2017/5/27.
 */
public class SysElderUserDTO  extends DataEntity<SysElderUserDTO> implements java.io.Serializable{

    private static final long serialVersionUID = -3649249427795059809L;

    private String id;

    private String sysUserID;

    private String easemobID;

    private String easemobPassword;

    private String memberCardID;

    private String loginToken;

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

    public String getMemberCardID() {
        return memberCardID;
    }

    public void setMemberCardID(String memberCardID) {
        this.memberCardID = memberCardID;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getSysHospitalID() {
        return sysHospitalID;
    }

    public void setSysHospitalID(String sysHospitalID) {
        this.sysHospitalID = sysHospitalID;
    }
}
