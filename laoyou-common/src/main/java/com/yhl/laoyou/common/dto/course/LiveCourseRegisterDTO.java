package com.yhl.laoyou.common.dto.course;

/**
 * Created by sunxiao on 2017/8/8.
 */
public class LiveCourseRegisterDTO {

    private Integer id;

    private Integer liveCourseId;

    private String elderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiveCourseId() {
        return liveCourseId;
    }

    public void setLiveCourseId(Integer liveCourseId) {
        this.liveCourseId = liveCourseId;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }
}
