package com.yhl.laoyou.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zbm84 on 2017/6/7.
 */
public class APPVersionDTO {

    @JSONField(name = "version")
    private String version;

    @JSONField(name = "introduction")
    private String introduction;

    @JSONField(name = "downloadURL")
    private String downloadURL;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String dowloadURL) {
        this.downloadURL = dowloadURL;
    }
}
