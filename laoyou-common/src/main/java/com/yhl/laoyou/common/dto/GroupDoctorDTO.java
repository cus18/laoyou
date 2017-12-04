package com.yhl.laoyou.common.dto;

/**
 * Created by zbm84 on 2017/6/8.
 */
public class GroupDoctorDTO {

    private String doctorID;
    private String doctorName;
    private String doctorPhoto;
    private Integer doctorType;

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }
}
