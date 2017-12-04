package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MenuDTO {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "menuDatas")
    private List<MenuDTO> menuDatas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuDTO> getMenuDatas() {
        return menuDatas;
    }

    public void setMenuDatas(List<MenuDTO> menuDatas) {
        this.menuDatas = menuDatas;
    }
}