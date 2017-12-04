package com.yhl.laoyou.common.dto;

import java.util.List;

/**
 * Created by zbm84 on 2017/6/8.
 */
public class GroupMemberDTO {

    private String ownerID;
    private String ownerName;
    private String ownerPhoto;
    private Integer ownerType;
    private String elderID;
    private String elderName;
    private String elderPhoto;
    private String elderMemberCardID;
    private String nurseName;

    private List<GroupDoctorDTO>  groupDoctorDTOList;

    public String getElderID() {
        return elderID;
    }

    public void setElderID(String elderID) {
        this.elderID = elderID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public List<GroupDoctorDTO> getGroupDoctorDTOList() {
        return groupDoctorDTOList;
    }

    public void setGroupDoctorDTOList(List<GroupDoctorDTO> groupDoctorDTOList) {
        this.groupDoctorDTOList = groupDoctorDTOList;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoto() {
        return ownerPhoto;
    }

    public void setOwnerPhoto(String ownerPhoto) {
        this.ownerPhoto = ownerPhoto;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderPhoto() {
        return elderPhoto;
    }

    public void setElderPhoto(String elderPhoto) {
        this.elderPhoto = elderPhoto;
    }

    public String getElderMemberCardID() {

        return elderMemberCardID;
    }

    public void setElderMemberCardID(String elderMemberCardID) {
        this.elderMemberCardID = elderMemberCardID;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }
}
