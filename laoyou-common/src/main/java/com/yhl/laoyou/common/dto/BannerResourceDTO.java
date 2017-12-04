package com.yhl.laoyou.common.dto;

import com.yhl.laoyou.common.persistence.DataEntity;
import com.yhl.laoyou.common.persistence.Page;

/**
 * Created by zbm84 on 2017/10/13.
 */
public class BannerResourceDTO extends DataEntity<BannerResourceDTO> {

    private Integer id;
    private String app;
    private String name;
    private String url;
    private String size;
    private Integer nums;
    private Integer del_flage;

    private String searchValue;

    private String type;

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getDel_flage() {
        return del_flage;
    }

    public void setDel_flage(Integer del_flage) {
        this.del_flage = del_flage;
    }
}
