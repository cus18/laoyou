package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.constant.StatusType;
import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.course.*;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.modules.courseService.service.LiveCourseService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 直播板块
 * @author frank
 * @date 2015-10-14
 */

@Controller
@RequestMapping(value = "laoyou/")
public class CourseController {

	@Autowired
	LiveCourseService liveCourseService;

	/**
	 * 获取近期将要开展的直播列表
	 *
	 *  input pageParamDTO
	 *  注意：pageParamDTO的范型requestData，为在线课程的label属性标签，在此来匹配不同类型的视频，如果为null，则表示所有的视频类型
	 *
	 *  output ResponseDTO<List<LiveCourseDTO>>
	 *
	 */
	@RequestMapping(value = "recentLiveBroadCast", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<LiveCourseDTO>> recentLiveBroadCast(@RequestBody PageParamDTO<String> pageParamDTO,
														 HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<LiveCourseDTO>> responseDTO = new ResponseDTO<>();
		if("page".equals(pageParamDTO.getRequestData())){
			Page page = liveCourseService.getLiveCourseByInfo(pageParamDTO, new String[]{StatusType.PREPARE.getValue(),StatusType.ONGOING.getValue()});
			responseDTO.setResponseData(page.getList());
		}else{
			List list = liveCourseService.getAllLiveCourseByInfo(new String[]{StatusType.PREPARE.getValue(),StatusType.ONGOING.getValue()});
			responseDTO.setResponseData(list);
		}
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取已经结束的直播列表
	 *
	 *  input pageParamDTO
	 *  注意：pageParamDTO的范型requestData，为在线课程的label属性标签，在此来匹配不同类型的视频，如果为null，则表示所有的视频类型
	 *
	 *
	 *  output ResponseDTO<List<LiveCourseDTO>>
	 *
	 */
	@RequestMapping(value = "historyLiveBroadCast", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<LiveCourseDTO>> historyLiveBroadCast(@RequestBody PageParamDTO<String> pageParamDTO,
														  HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<LiveCourseDTO>> responseDTO = new ResponseDTO<>();
		if("page".equals(pageParamDTO.getRequestData())){
			Page page = liveCourseService.getLiveCourseByInfo(pageParamDTO, new String[]{StatusType.FINISH.getValue()});
			responseDTO.setResponseData(page.getList());
		}else{
			List list = liveCourseService.getAllLiveCourseByInfo(new String[]{StatusType.FINISH.getValue()});
			responseDTO.setResponseData(list);
		}
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取某个直播课程的详细信息
	 *
	 *  input LiveCourseDTO
	 *
	 *  output ResponseDTO<LiveCourseDTO>
	 *
	 */
	@RequestMapping(value = "liveBroadCastDetail", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<LiveCourseDTO> liveBroadCastDetail(@RequestBody LiveCourseDTO liveCourseDTO,
													HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<LiveCourseDTO> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);
		if(user!=null){
			liveCourseDTO = liveCourseService.getLiveBroadCastDetail(user.getSysElderUserDTO().getId(),liveCourseDTO);
			responseDTO.setResponseData(liveCourseDTO);
			responseDTO.setResult(StatusConstant.SUCCESS);
		}else{
			responseDTO.setResult(StatusConstant.FAILURE);
			responseDTO.setErrorInfo("没有获取到用户信息！");
		}
		return responseDTO;
	}

	/**
	 * 报名某个直播课程
	 *
	 *  input LiveCourseDTO
	 *
	 *  output ResponseDTO<LiveCourseDTO>
	 *
	 */
	@RequestMapping(value = "liveBroadCast/register", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<LiveCourseDTO> registerLiveBroadCast(@RequestBody LiveCourseDTO liveCourseDTO,
												   HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<LiveCourseDTO> responseDTO = new ResponseDTO<>();

		User user = UserService.getUser(request);
		LiveCourseRegisterDTO dto = new LiveCourseRegisterDTO();
		if(user!=null){
			dto.setElderId(user.getSysElderUserDTO().getId());
			dto.setLiveCourseId(liveCourseDTO.getLiveCourseId());
			liveCourseService.registerLiveBroadCast(dto);
			responseDTO.setResult(StatusConstant.SUCCESS);
		}else{
			responseDTO.setResult(StatusConstant.FAILURE);
			responseDTO.setErrorInfo("没有查到用户id");
		}
		return responseDTO;
	}


	/**
	 * 获取在线课程的视频列表
	 *
	 *  input pageParamDTO<String>
	 *  注意：pageParamDTO的范型requestData，为在线课程的label属性标签，在此来匹配不同类型的视频，
	 *  如果为""，即空字符串，如果为"health"，则表示所有的健康类视频类型，如果为"XXX"，则表示所谓视频为XXX类视频
	 *
	 *  output ResponseDTO<List<OnlineCourseDTO>>
	 *
	 */
	@RequestMapping(value = "onlineCourseList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<OnlineCourseDTO>> onlineCourseList(@RequestBody PageParamDTO<String> pageParamDTO,
														HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<OnlineCourseDTO>> responseDTO = new ResponseDTO<>();

		List<OnlineCourseDTO> list = liveCourseService.getOnlineCourseList(pageParamDTO);
		responseDTO.setResponseData(list);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 *
	 * 获取在线课程的的详细信息
	 *
	 *  input pageParamDTO
	 *
	 *  output ResponseDTO<OnlineCourseDTO>
	 *
	 */
	@RequestMapping(value = "onlineCourseDetail", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<OnlineCourseDTO> onlineCourseDetail(@RequestBody OnlineCourseDTO onlineCourseDTO,
													HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<OnlineCourseDTO> responseDTO = new ResponseDTO<>();

		onlineCourseDTO = liveCourseService.getOnlineCourseDetail(onlineCourseDTO);
		responseDTO.setResponseData(onlineCourseDTO);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取某个在线课程的的所有评论
	 *
	 *  input PageParamDTO<string> string为在线课程的ID
	 *
	 *  output ResponseDTO<HashMap<String,Object>> HashMap<String,Object>里面，
	 *  放入List<OnlineCourseDiscussDTO>和onlineCourseDiscussNum
	 *
	 */
	@RequestMapping(value = "onlineCourseDiscuss", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<Page> onlineCourseDiscuss(@RequestBody PageParamDTO<String> pageParamDTO,
															HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<Page> responseDTO = new ResponseDTO<>();
		Page page = liveCourseService.getOnlineCourseDiscuss(pageParamDTO);
		responseDTO.setResponseData(page);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 对某个在线课程进行评价
	 *
	 *  input OnlineCourseDiscussDTO
	 *
	 *  output ResponseDTO<List<OnlineCourseDiscussDTO>>
	 *
	 */
	@RequestMapping(value = "onlineCourseDiscuss/create", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<OnlineCourseDiscussDTO>> createOnlineCourseDiscuss(@RequestBody OnlineCourseDiscussDTO onlineCourseDiscussDTO,
																		HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<OnlineCourseDiscussDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);
		if(user!=null){
			onlineCourseDiscussDTO.setElderId(user.getSysElderUserDTO().getId());
			onlineCourseDiscussDTO.setElderName(user.getName());
			liveCourseService.createOnlineCourseDiscuss(onlineCourseDiscussDTO);
			responseDTO.setResult(StatusConstant.SUCCESS);
		}else{
			responseDTO.setResult(StatusConstant.FAILURE);
			responseDTO.setErrorInfo("没有获取到用户信息！");
		}
		return responseDTO;
	}

	/**
	 * 学习和收藏的课程
	 *
	 *  input OnlineCourseDiscussDTO
	 *
	 *  output ResponseDTO<List<OnlineCourseDiscussDTO>>
	 *
	 */
	@RequestMapping(value = "getMyOnlineCourse", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<OnlineCourseMyCourseDTO>> collectionCourse(@RequestBody PageParamDTO<String> pageParamDTO,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<OnlineCourseMyCourseDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);
		OnlineCourseMyCourseDTO onlineCourseMyCourseDTO = new OnlineCourseMyCourseDTO();
		if(user!=null){
			onlineCourseMyCourseDTO.setElderId(user.getSysElderUserDTO().getId());
			List<OnlineCourseMyCourseDTO> list = liveCourseService.getMyOnlineCourse(onlineCourseMyCourseDTO,pageParamDTO);
			responseDTO.setResponseData(list);
			responseDTO.setResult(StatusConstant.SUCCESS);
		}else{
			responseDTO.setResult(StatusConstant.FAILURE);
			responseDTO.setErrorInfo("没有获取到用户信息！");
		}
		return responseDTO;
	}
}
