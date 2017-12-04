package com.yhl.laoyou.common.dto.practitioner.Settings;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zbm84 on 2017/5/5.
 */
public class UserInfoDTO {

    @JSONField(name = "userID")
    private String userID;

    @JSONField(name = "name")
    private String name;


    @JSONField(name = "age")
    private String age;

    @JSONField(name = "gender")
    private String gender;

    @JSONField(name = "area")
    private String area;

    @JSONField(name = "headImage")
    private String headImage;

    @JSONField(name = "hospitalName")
    private String hospitalName;

    @JSONField(name = "department")
    private String department;

    @JSONField(name = "title")
    private String title;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHospitalName() {
        return hospitalName;
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
}
