package com.yhl.laoyou.common.dto.course;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhl.laoyou.common.persistence.DataEntity;

import java.util.List;

public class OnlineCourseDTO extends DataEntity<OnlineCourseDTO> {

    //在线课程ID号
    @JSONField(name = "onlineCourseId")
    private Integer onlineCourseId;

    //在线课程的名称
    @JSONField(name = "onlineCourseName")
    private String onlineCourseName;

    //在线课程的发布人
    @JSONField(name = "onlineCourseProvider")
    private String onlineCourseProvider;

    //在线课程的logo
    @JSONField(name = "onlineCourseLogo")
    private String onlineCourseLogo;

    //在线课程的时长
    @JSONField(name = "onlineCourseDuration")
    private String onlineCourseDuration;

    //在线课程的属性标签
    //例如：健康;体育;手工
    @JSONField(name = "onlineCourseLabel")
    private String onlineCourseLabel;

    @JSONField(name = "onlineCourseLabelIds")
    private List<String> onlineCourseLabelIds;

    @JSONField(name = "onlineCourseLabelId")
    private String onlineCourseLabelId;

    //在线课程老师名称
    @JSONField(name = "onlineCourseTeacherName")
    private String onlineCourseTeacherName;

    //在线课程老师级别，例如金牌讲师
    @JSONField(name = "onlineCourseTeacherLevel")
    private String onlineCourseTeacherLevel;

    //在线课程的提纲
    @JSONField(name = "onlineCourseOutline")
    private String onlineCourseOutline;

    //在线课程的子课程列表
    @JSONField(name = "liveCourseContentIntro")
    private List<OnlineCourseDataDTO> onLineCourseDataDTOList;

    //在线课程的学习人数
    @JSONField(name = "onlineCourseStudyNum")
    private String onlineCourseStudyNum;


    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer onlineCourseId) {
        this.onlineCourseId = onlineCourseId;
    }

    public String getOnlineCourseName() {
        return onlineCourseName;
    }

    public void setOnlineCourseName(String onlineCourseName) {
        this.onlineCourseName = onlineCourseName;
    }

    public String getOnlineCourseProvider() {
        return onlineCourseProvider;
    }

    public void setOnlineCourseProvider(String onlineCourseProvider) {
        this.onlineCourseProvider = onlineCourseProvider;
    }

    public String getOnlineCourseLogo() {
        return onlineCourseLogo;
    }

    public void setOnlineCourseLogo(String onlineCourseLogo) {
        this.onlineCourseLogo = onlineCourseLogo;
    }

    public String getOnlineCourseDuration() {
        return onlineCourseDuration;
    }

    public void setOnlineCourseDuration(String onlineCourseDuration) {
        this.onlineCourseDuration = onlineCourseDuration;
    }

    public String getOnlineCourseLabel() {
        return onlineCourseLabel;
    }

    public void setOnlineCourseLabel(String onlineCourseLabel) {
        this.onlineCourseLabel = onlineCourseLabel;
    }

    public String getOnlineCourseTeacherName() {
        return onlineCourseTeacherName;
    }

    public void setOnlineCourseTeacherName(String onlineCourseTeacherName) {
        this.onlineCourseTeacherName = onlineCourseTeacherName;
    }

    public String getOnlineCourseTeacherLevel() {
        return onlineCourseTeacherLevel;
    }

    public void setOnlineCourseTeacherLevel(String onlineCourseTeacherLevel) {
        this.onlineCourseTeacherLevel = onlineCourseTeacherLevel;
    }

    public List<String> getOnlineCourseLabelIds() {
        return onlineCourseLabelIds;
    }

    public void setOnlineCourseLabelIds(List<String> onlineCourseLabelIds) {
        this.onlineCourseLabelIds = onlineCourseLabelIds;
    }

    public String getOnlineCourseOutline() {
        return onlineCourseOutline;
    }

    public void setOnlineCourseOutline(String onlineCourseOutline) {
        this.onlineCourseOutline = onlineCourseOutline;
    }

    public List<OnlineCourseDataDTO> getOnLineCourseDataDTOList() {
        return onLineCourseDataDTOList;
    }

    public void setOnLineCourseDataDTOList(List<OnlineCourseDataDTO> onLineCourseDataDTOList) {
        this.onLineCourseDataDTOList = onLineCourseDataDTOList;
    }

    public String getOnlineCourseLabelId() {
        return onlineCourseLabelId;
    }

    public void setOnlineCourseLabelId(String onlineCourseLabelId) {
        this.onlineCourseLabelId = onlineCourseLabelId;
    }

    public String getOnlineCourseStudyNum() {
        return onlineCourseStudyNum;
    }

    public void setOnlineCourseStudyNum(String onlineCourseStudyNum) {
        this.onlineCourseStudyNum = onlineCourseStudyNum;
    }
}