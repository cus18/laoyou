package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zbm84 on 2017/5/26.
 */
public class MenuParameterDTO {

    @JSONField(name = "menuType")
    private String menuType;

    @JSONField(name = "menuDatas")
    private List<MenuDTO> menuDatas;

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<MenuDTO> getMenuDatas() {
        return menuDatas;
    }

    public void setMenuDatas(List<MenuDTO> menuDatas) {
        this.menuDatas = menuDatas;
    }
}
