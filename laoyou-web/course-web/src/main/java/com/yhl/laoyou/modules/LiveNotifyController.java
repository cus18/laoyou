package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusType;
import com.yhl.laoyou.common.dto.course.LiveCourseDTO;
import com.yhl.laoyou.common.utils.ConstantUtil;
import com.yhl.laoyou.modules.courseService.service.LiveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sunxiao on 2017/8/10.
 */
@Controller
@RequestMapping(value = "laoyou/liveNotify")
public class LiveNotifyController {

    @Autowired
    LiveCourseService liveCourseService;

    /**
     * 推流断流回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "publishAndDoneStreamNotify", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    void publishAndDoneStreamNotify(HttpServletRequest request,HttpServletResponse response) {
        String action = request.getParameter("action");
        LiveCourseDTO liveCourseDTO = new LiveCourseDTO();
        if("publish".equals(action)){
            liveCourseDTO.setLiveCourseStatus(StatusType.ONGOING.getValue());
        }else{
            liveCourseDTO.setLiveCourseStatus(StatusType.FINISH.getValue());
        }
        liveCourseDTO.setId(request.getParameter("id").replace(ConstantUtil.LIVE_STREAMNAME,""));
        liveCourseService.updateLiveCourse(liveCourseDTO);
    }

    /**
     * 录制回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "recordingNotify", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    String recordingNotify(HttpServletRequest request,HttpServletResponse response) {
        Map map = request.getParameterMap();
        System.out.println(map);
        return "";
    }

    /**
     * 连麦回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "conMicrophoneNotify", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    String conMicrophoneNotify(HttpServletRequest request,HttpServletResponse response) {
        Map map = request.getParameterMap();
        System.out.println(map);
        return "";
    }

}
