package com.yhl.laoyou.common.dto.course;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.Date;

public class LiveCourseDTO extends DataEntity<LiveCourseDTO>{

    //直播课程ID号
    @JSONField(name = "liveCourseId")
    private Integer liveCourseId;

    //直播课程的Logo的URL
    @JSONField(name = "liveCourseLogo")
    private String liveCourseLogo;

    //直播课程的属性标签
    //例如：健康;体育;手工
    @JSONField(name = "liveCourseLabel")
    private String liveCourseLabel;

    //直播课程的开播时间 2017-06-08 10：00
    @JSONField(name = "liveCourseStartDate")
    private String liveCourseStartDate;

    //直播课程的标题
    @JSONField(name = "liveCourseTitle")
    private String liveCourseTitle;

    //直播课程的举办地址
    @JSONField(name = "liveCourseAddress")
    private String liveCourseAddress;

    //直播课程的介绍
    @JSONField(name = "liveCourseSpeakerName")
    private String liveCourseSpeakerName;

    //直播课程的介绍
    @JSONField(name = "liveCourseSpeakerIntro")
    private String liveCourseSpeakerIntro;

    //直播课程的时长
    @JSONField(name = "liveCourseDuration")
    private String liveCourseDuration;

    //直播课程的议程
    @JSONField(name = "liveCourseContentIntro")
    private String liveCourseContentIntro;

    //直播课程的状态
    /**
     * prepare 状态表示直播还没有开始
     * ongoing 状态表示直播正在进行中
     * finish 状态表示直播已经结束了
     * */
    @JSONField(name = "liveCourseStatus")
    private String liveCourseStatus;

    private String[] liveCourseStatusArray;

    //直播课程的正在收看的人数
    @JSONField(name = "liveCourseAttendNum")
    private Integer liveCourseAttendNum;

    //用户关于此直播课程的报名状态
    /**
     * 0 没有报名
     * 1 已经报名
     * */
    @JSONField(name = "liveCourseRegisterStatus")
    private String liveCourseRegisterStatus;

    //此直播课程的播放URL
    @JSONField(name = "liveCourseURL")
    private String liveCourseURL;

    public String getLiveCourseLogo() {
        return liveCourseLogo;
    }

    public void setLiveCourseLogo(String liveCourseLogo) {
        this.liveCourseLogo = liveCourseLogo;
    }

    public String getLiveCourseLabel() {
        return liveCourseLabel;
    }

    public void setLiveCourseLabel(String liveCourseLabel) {
        this.liveCourseLabel = liveCourseLabel;
    }

    public String getLiveCourseStartDate() {
        return liveCourseStartDate;
    }

    public void setLiveCourseStartDate(String liveCourseStartDate) {
        this.liveCourseStartDate = liveCourseStartDate;
    }

    public Integer getLiveCourseId() {
        return liveCourseId;
    }

    public void setLiveCourseId(Integer liveCourseId) {
        this.liveCourseId = liveCourseId;
    }

    public Integer getLiveCourseAttendNum() {
        return liveCourseAttendNum;
    }

    public void setLiveCourseAttendNum(Integer liveCourseAttendNum) {
        this.liveCourseAttendNum = liveCourseAttendNum;
    }

    public String getLiveCourseTitle() {
        return liveCourseTitle;
    }

    public void setLiveCourseTitle(String liveCourseTitle) {
        this.liveCourseTitle = liveCourseTitle;
    }

    public String getLiveCourseAddress() {
        return liveCourseAddress;
    }

    public void setLiveCourseAddress(String liveCourseAddress) {
        this.liveCourseAddress = liveCourseAddress;
    }

    public String getLiveCourseSpeakerIntro() {
        return liveCourseSpeakerIntro;
    }

    public String[] getLiveCourseStatusArray() {
        return liveCourseStatusArray;
    }

    public void setLiveCourseStatusArray(String[] liveCourseStatusArray) {
        this.liveCourseStatusArray = liveCourseStatusArray;
    }

    public void setLiveCourseSpeakerIntro(String liveCourseSpeakerIntro) {
        this.liveCourseSpeakerIntro = liveCourseSpeakerIntro;
    }

    public String getLiveCourseSpeakerName() {
        return liveCourseSpeakerName;
    }

    public void setLiveCourseSpeakerName(String liveCourseSpeakerName) {
        this.liveCourseSpeakerName = liveCourseSpeakerName;
    }

    public String getLiveCourseDuration() {
        return liveCourseDuration;
    }

    public void setLiveCourseDuration(String liveCourseDuration) {
        this.liveCourseDuration = liveCourseDuration;
    }

    public String getLiveCourseContentIntro() {
        return liveCourseContentIntro;
    }

    public void setLiveCourseContentIntro(String liveCourseContentIntro) {
        this.liveCourseContentIntro = liveCourseContentIntro;
    }

    public String getLiveCourseStatus() {
        return liveCourseStatus;
    }

    public void setLiveCourseStatus(String liveCourseStatus) {
        this.liveCourseStatus = liveCourseStatus;
    }

    public String getLiveCourseRegisterStatus() {
        return liveCourseRegisterStatus;
    }

    public void setLiveCourseRegisterStatus(String liveCourseRegisterStatus) {
        this.liveCourseRegisterStatus = liveCourseRegisterStatus;
    }

    public String getLiveCourseURL() {
        return liveCourseURL;
    }

    public void setLiveCourseURL(String liveCourseURL) {
        this.liveCourseURL = liveCourseURL;
    }
}