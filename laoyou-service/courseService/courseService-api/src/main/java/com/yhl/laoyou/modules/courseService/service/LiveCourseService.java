package com.yhl.laoyou.modules.courseService.service;

import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.course.*;
import com.yhl.laoyou.common.persistence.Page;

import java.util.List;

/**
 * Created by sunxiao on 2017/8/8.
 */

public interface LiveCourseService {

    Page getLiveCourseByInfo(PageParamDTO<String> pageParamDTO,String[] status);

    List<LiveCourseDTO> getAllLiveCourseByInfo(String[] status);

    LiveCourseDTO getLiveBroadCastDetail(String elderId,LiveCourseDTO liveCourseDTO);

    void registerLiveBroadCast(LiveCourseRegisterDTO dto);

    List<OnlineCourseDTO> getOnlineCourseList(PageParamDTO<String> pageParamDTO);

    OnlineCourseDTO getOnlineCourseDetail(OnlineCourseDTO onlineCourseDTO);

    Page getOnlineCourseDiscuss(PageParamDTO<String> pageParamDTO);

    void createOnlineCourseDiscuss(OnlineCourseDiscussDTO onlineCourseDiscussDTO);

    void updateLiveCourse(LiveCourseDTO liveCourseDTO);

    List<OnlineCourseMyCourseDTO> getMyOnlineCourse(OnlineCourseMyCourseDTO dto,PageParamDTO<String> pageParamDTO);

    List<OnlineCourseDTO> findOnlineCoursePage(PageParamDTO<String> pageParamDTO);

}
