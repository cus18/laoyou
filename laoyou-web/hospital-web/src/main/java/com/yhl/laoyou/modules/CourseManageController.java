package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.course.OnlineCourseDTO;
import com.yhl.laoyou.common.dto.course.OnlineCourseMyCourseDTO;
import com.yhl.laoyou.modules.courseService.service.LiveCourseService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunxiao on 2017/10/9.
 */
@Controller
@RequestMapping(value = "courseManage")
public class CourseManageController {

    @Autowired
    LiveCourseService liveCourseService;

    /**
     * 分页查询视频列表
     * @param pageParamDTO
     * @return
     */
    @RequestMapping(value="/searchCourse",method = {RequestMethod.POST, RequestMethod.GET})
    public
    //@LoginRequired
    @ResponseBody
    ResponseDTO<List<OnlineCourseDTO>> searchCourse(@RequestBody PageParamDTO<String> pageParamDTO) {
        ResponseDTO<List<OnlineCourseDTO>> responseDTO = new ResponseDTO<>();
        List<OnlineCourseDTO> list = null;
        try{
            list = liveCourseService.findOnlineCoursePage(pageParamDTO);
            responseDTO.setResponseData(list);
            responseDTO.setResult(StatusConstant.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setErrorInfo("查询出错！");
        }
        return responseDTO;
    }

}
