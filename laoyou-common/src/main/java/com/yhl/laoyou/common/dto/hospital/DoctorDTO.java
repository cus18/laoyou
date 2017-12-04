package com.yhl.laoyou.common.dto.hospital;


import com.yhl.laoyou.common.persistence.DataEntity;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.excel.ExcelField;

public class DoctorDTO extends DataEntity<DoctorDTO> {

    private String id;

    @ExcelField(title="姓名")
    private String name;

    @ExcelField(title="性别")
    private String gender;

    @ExcelField(title="手机")
    private String phone;

    @ExcelField(title="医师职称")
    private String title;

    @ExcelField(title="所在科室")
    private String department;

    private String memberNum;

    private String serviceNum;

    private String easemobID;

    @ExcelField(title="类别")
    private String type;

    @ExcelField(title="地区")
    private String area;



    public String getEasemobID() {
        return easemobID;
    }

    public void setEasemobID(String easemobID) {
        this.easemobID = easemobID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
