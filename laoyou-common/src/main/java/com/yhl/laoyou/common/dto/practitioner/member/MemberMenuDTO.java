package com.yhl.laoyou.common.dto.practitioner.member;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberMenuDTO extends MemberDTO {

    @JSONField(name = "menuData")
    private String menuData;

    public String getMenuData() {
        return menuData;
    }

    public void setMenuData(String menuData) {
        this.menuData = menuData;
    }
}