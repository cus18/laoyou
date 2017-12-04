package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.activity.ActivityDTO;
import com.yhl.laoyou.common.dto.activity.ActivityDiscussDTO;
import com.yhl.laoyou.common.dto.activity.AttendedActivityDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.modules.activityService.entity.Activity;
import com.yhl.laoyou.modules.myService.service.ActivityService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动
 */
@Controller
@RequestMapping(value = "laoyou/")
public class ActivityController {


	@Autowired
	private ActivityService activityService;


	/**
	 * 用户创建活动
	 * @param activity
	 * @param request
	 * @return 活动的ID
	 */
	@RequestMapping(value = "createActivity", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<String> createActivity(@RequestBody Activity activity, HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		User user = UserService.getUser(request);

		ResponseDTO responseDTO = new ResponseDTO<>();
		responseDTO.setResponseData(activityService.addActivity(activity,user));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 创建群组
	 * @param activityID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityDiscuss/createActivityEasemobGroup", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO createActivityEasemobGroup(@RequestParam String activityID,
										   HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDiscussDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);

		activityService.createActivityEasemobGroup(activityID,user);

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 用户报名参加某个活动
	 *
	 *  input PageParamDTO<List<String>> String中放入的是报名的用户的elderId列表,['vjwioejgewoi','vwejoigjweoigj','fiweohgwng']
	 *
	 *  output ResponseDTO<String> 返回的String中，此次活动所对应的群组ID
	 *
	 */
	@RequestMapping(value = "joinActivity", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<String> joinActivity(@RequestParam List<String> elderList,
							 @RequestParam String activityId,
							 HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO responseDTO = new ResponseDTO<>();

		/****
		 List<String>中放入的是报名参加活动的用户列表，为用户的elderId值，['vjwioejgewoi','vwejoigjweoigj','fiweohgwng']
		 *****/
		//responseData里面放入的是此次活动所对应的群组ID
		responseDTO.setResponseData(activityService.addActivityUser(activityId,elderList));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 用户加入活动群聊
	 * @param activityID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityDiscuss/joinActivityEasemobGroup", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO joinActivityEasemobGroup(@RequestParam String activityID,
									  HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDiscussDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);

		/****
		 根据活动的ID号，用户对某个活动发表评论
		 *****/
		activityService.joinActivityEasemobGroup(activityID,user);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取已参与的活动群列表信息
	 *
	 *  input PageParamDTO,
	 *
	 *  output ResponseDTO<List<AttendedActivityDTO>>
	 *
	 */
	@RequestMapping(value = "attendedActivityGroupMessage", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<AttendedActivityDTO>> attendedActivityGroupMessage(@RequestBody PageParamDTO pageParamDTO,
																  HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO<List<AttendedActivityDTO>> responseDTO = new ResponseDTO<>();

		/****
		 当前用户已参与的活动群列表信息，每条信息的内容参考List<AttendedActivityDTO>
		 *****/
		responseDTO.setResponseData(activityService.getActivityByElderUser(UserService.getUser(request).getSysElderUserDTO().getId()));

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取所有活动的列表
	 *
	 *  input PageParamDTO<String>
	 *  注意：requestData此处为String类型，
	 *  当为0的时候，表示是所有的活动列表，不管用户参加或者没有参加的
	 *  当为1的时候，表示是用户自己已经报名参加的所有活动的列表
	 *  当为2的时候，表示是系统里面的热门活动列表，依据是报名参加人数最多的活动
	 *
	 *  output ResponseDTO<List<com.yhl.laoyou.modules.activityService.entity.ActivityDTO>> 比例搭配，没有开始:进行中:已结束 比例为4:3:3
	 *
	 */
	@RequestMapping(value = "activityList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<ActivityDTO>> activityList(@RequestBody PageParamDTO<String> pageParamDTO,
												HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);

		/****
		 获取系统中活动列表信息，每条信息的内容参考List<com.yhl.laoyou.modules.activityService.entity.ActivityDTO>
		 *****/
		String elderID = pageParamDTO.getRequestData().equals("1")?UserService.getUser(request).getSysElderUserDTO().getId():null;
		responseDTO.setResponseData(activityService.getActivityList(elderID,pageParamDTO.getPageNo(),pageParamDTO.getRequestData()));

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}


	/**
	 * 获取某个活动的详细信息
	 *
	 *  input activityId,
	 *
	 *  output ResponseDTO<com.yhl.laoyou.modules.activityService.entity.ActivityDTO>
	 *
	 */
	@RequestMapping(value = "activityDetail", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<ActivityDTO> activityDetail(@RequestParam String activityId,
											HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<ActivityDTO> responseDTO = new ResponseDTO<>();

		/****
		 根据活动的ID号，获取活动的详细信息，放入ActivityDTO中
		 *****/
		responseDTO.setResponseData(activityService.getActivity(activityId));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取用户是否参加了某个活动的状态
	 *
	 *  input activityId
	 *
	 *  output ResponseDTO<String> 如果String为"1"代表已经报名参加，如果为"0"，则代表没有报名参加
	 *
	 */
	@RequestMapping(value = "activityAttendStatus", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<String> activityAttendStatus(@RequestParam String activityId,
											HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO<String> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);

		/****
		 根据活动的ID号，和用户的信息，判断此用户是否报名参加了此活动，"1"代表已经报名参加，如果为"0"，则代表没有报名参加
		 *****/
		responseDTO.setResponseData(activityService.getActivityAttendStatus(activityId,user.getSysElderUserDTO().getId())>0?"1":"0");
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取某个活动的评论信息
	 *
	 *  input activityId,
	 *
	 *  output ResponseDTO<List<ActivityDiscussDTO>>
	 *
	 */
	@RequestMapping(value = "activityDiscuss", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<ActivityDiscussDTO>> activityDiscuss(@RequestBody PageParamDTO<String> pageParamDTO,
														  HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO<List<ActivityDiscussDTO>> responseDTO = new ResponseDTO<>();

		/****
		 根据活动的ID号，获取活动的评论信息，放入List<ActivityDiscussDTO>中
		 *****/
		responseDTO.setResponseData(activityService.getActivityDiscuss(pageParamDTO.getRequestData(),Integer.parseInt(pageParamDTO.getPageNo())*Integer.parseInt(pageParamDTO.getPageSize())));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 用户针对对某个活动的发表评论
	 *
	 *  input activityDiscussDTO,
	 *
	 *  output ResponseDTO<List<ActivityDiscussDTO>>
	 *
	 */
	@RequestMapping(value = "activityDiscuss/create", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO CreateActivityDiscuss(@RequestBody ActivityDiscussDTO activityDiscussDTO,
																HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDiscussDTO>> responseDTO = new ResponseDTO<>();
		User user = UserService.getUser(request);

		/****
		 根据活动的ID号，用户对某个活动发表评论
		 *****/
		activityDiscussDTO.setElderId(user.getSysElderUserDTO().getId());
		activityService.addActivityDiscuss(activityDiscussDTO);

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 根据关键字搜索活动
	 * @param search 关键字
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityDiscuss/activityListBySearch", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO activityListBySearch(@RequestParam String search,
									  HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDTO>> responseDTO = new ResponseDTO<>();

		responseDTO.setResponseData(activityService.getActivityListBySearch(search));

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 收藏活动
	 * @param activityID 活动ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityDiscuss/addActivityFavorite", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO addActivityFavorite(@RequestParam String activityID,
									 HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDTO>> responseDTO = new ResponseDTO<>();

		activityService.addActivityFavorite(activityID,UserService.getUser(request).getSysElderUserDTO().getId());

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}


	/**
	 * 取消收藏活动
	 * @param activityID 活动ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityDiscuss/delActivityFavorite", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO delActivityFavorite(@RequestParam String activityID,
									HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDTO>> responseDTO = new ResponseDTO<>();

		activityService.delActivityFavorite(activityID,UserService.getUser(request).getSysElderUserDTO().getId());

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取已收藏的活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getMyActivityFavorite", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO getMyActivityFavorite(@RequestParam String type,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();

		if(type.equals("1")) {
			responseDTO.setResponseData(activityService.getMyFavoriteActivityList(UserService.getUser(request).getSysElderUserDTO().getId()));
		}else{
			responseDTO.setResponseData(activityService.getMyFavoriteActivityList(UserService.getUser(request).getSysElderUserDTO().getId()).get(0));
		}
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取首页活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityListByFirstPage", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<ActivityDTO>> activityListByFirstPage(HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<ActivityDTO>> responseDTO = new ResponseDTO<>();

		/****
		 获取系统中活动列表信息，每条信息的内容参考List<com.yhl.laoyou.modules.activityService.entity.ActivityDTO>
		 *****/
		String hospitalID=UserService.getUser(request).getSysElderUserDTO().getSysHospitalID();
		responseDTO.setResponseData(activityService.activityListByFirstPage(hospitalID));

		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取活动列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityListByBackEnd", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO activityListByBackEnd(HttpServletRequest request,
										 @RequestParam String status,
										 @RequestParam Integer pageNo,
										 @RequestParam Integer pageSize,
										 @RequestParam String searchValue,
										 @RequestParam String startDate,
										 @RequestParam String endDate) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();
		String hospitalID=UserService.getUser(request).getSysHospitalUserDTO().getId();
		responseDTO.setResponseData(activityService.activityListByBackEnd(hospitalID,status,pageNo,pageSize,searchValue,startDate,endDate));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}


	/**
	 * 获取某个活动报名参加人数的情况
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "activityUserListByBackEnd", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO activityUserListByBackEnd(HttpServletRequest request,
									  @RequestParam(required = true) String activityID) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();
		responseDTO.setResponseData(activityService.activityUserListByBackEnd(activityID));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 删除活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delActivity", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO delActivity(HttpServletRequest request,
										  @RequestParam(required = true) String activityID) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();
		responseDTO.setResponseData(activityService.delActivity(activityID));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 获取所有机构
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAllHospital", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO getAllHospital(HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();
		responseDTO.setResponseData(activityService.getAllHospital());
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 后台获取所有活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAllActivityListByBackEnd", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO getAllActivityListByBackEnd(HttpServletRequest request, @RequestBody ActivityDTO activityDTO) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDTO = new ResponseDTO<>();
		Page page=new Page();
		page.setPageNo(activityDTO.getPageNo());
		page.setPageSize(activityDTO.getPageSize());
		responseDTO.setResponseData(activityService.getAllActivityListByBackEnd(activityDTO,new Page()));
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}
}
