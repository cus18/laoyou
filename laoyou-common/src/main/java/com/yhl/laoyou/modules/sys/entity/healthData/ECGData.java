package com.yhl.laoyou.modules.sys.entity.healthData;

/**
 * Created by sunxiao on 2017/5/15.
 */
public class ECGData extends BaseAtr{

    private String type;
    private String ecgNew;
    private String ecgImg;
    private String heartRate;
    private String ecgFileRawUrl;
    private String ecgFileProcessedUrl;
    private String ecgReportUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEcgNew() {
        return ecgNew;
    }

    public void setEcgNew(String ecgNew) {
        this.ecgNew = ecgNew;
    }

    public String getEcgImg() {
        return ecgImg;
    }

    public void setEcgImg(String ecgImg) {
        this.ecgImg = ecgImg;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getEcgFileRawUrl() {
        return ecgFileRawUrl;
    }

    public void setEcgFileRawUrl(String ecgFileRawUrl) {
        this.ecgFileRawUrl = ecgFileRawUrl;
    }

    public String getEcgFileProcessedUrl() {
        return ecgFileProcessedUrl;
    }

    public void setEcgFileProcessedUrl(String ecgFileProcessedUrl) {
        this.ecgFileProcessedUrl = ecgFileProcessedUrl;
    }

    public String getEcgReportUrl() {
        return ecgReportUrl;
    }

    public void setEcgReportUrl(String ecgReportUrl) {
        this.ecgReportUrl = ecgReportUrl;
    }
}
