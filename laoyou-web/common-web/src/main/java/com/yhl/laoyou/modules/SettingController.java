package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.FeedbackDTO;
import com.yhl.laoyou.modules.myService.service.FeedbackService;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于账户管理
 * @author frank
 * @date 2015-10-14
 */
@Controller
@RequestMapping(value = "")
public class SettingController {

	private static Lock lock = new ReentrantLock();

	private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();


	@Autowired
	private FeedbackService feedbackService;


	/**
	 * 意见反馈
	 *
	 *
	 */
	@RequestMapping(value = "laoyou/feedback", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<FeedbackDTO> feedback(@RequestBody FeedbackDTO feedbackDto, HttpServletRequest request) {
		User user= UserService.getUser(request);
		feedbackDto.setUserID(user.getId());
		feedbackService.insertFeedback(feedbackDto);
		ResponseDTO<FeedbackDTO> responseDto=new ResponseDTO<>();
		if (feedbackService.insertFeedback(feedbackDto)>0){
			responseDto.setResult(StatusConstant.SUCCESS);
			responseDto.setErrorInfo("反馈成功");
			return responseDto;
		}else {
			responseDto.setResult(StatusConstant.FAILURE);
			responseDto.setErrorInfo("反馈失败");
			return responseDto;
		}
	}

}
