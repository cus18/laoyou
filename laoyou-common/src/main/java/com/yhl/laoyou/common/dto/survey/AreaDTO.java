package com.yhl.laoyou.common.dto.survey;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class AreaDTO {

    @JSONField(name = "id")
    private String id;

    //题目ID号
    @JSONField(name = "name")
    private String name;

    //题目名称
    @JSONField(name = "parentid")
    private String parentid;

    //题目名称
    @JSONField(name = "parentname")
    private String parentname;

    //题目名称
    @JSONField(name = "areacode")
    private String areacode;

    //题目名称
    @JSONField(name = "zipcode")
    private String zipcode;

    //题目名称
    @JSONField(name = "depth")
    private String depth;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}