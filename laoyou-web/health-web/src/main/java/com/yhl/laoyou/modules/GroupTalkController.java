package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.talk.GroupTalkDTO;
import com.yhl.laoyou.common.dto.practitioner.talk.TalkMessageListDTO;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于账户管理
 * @author frank
 * @date 2015-10-14
 */
@Controller
@RequestMapping(value = "health/")
public class GroupTalkController {

	private static Lock lock = new ReentrantLock();

	/**
	 * 健康宝首页，分页获取本医护人员所管理的健康服务群
	 *
	 * input PageParamDto
	 *
	 * output GroupTalkListDto
	 *
	 */
	@RequestMapping(value = "groupTalkList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<GroupTalkDTO>> groupTalkList(@RequestBody PageParamDTO pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<GroupTalkDTO>> responseDto = new ResponseDTO<List<GroupTalkDTO>>();
		List<GroupTalkDTO> groupTalkListDto = new ArrayList<GroupTalkDTO>();
		GroupTalkDTO groupTalkDto = new GroupTalkDTO();

		return responseDto;
	}

	/**
	 * 健康宝聊天详情页，分页获取本医护人员所管理的健康服务群内的聊天信息
	 *
	 * input PageParamDto
	 *
	 * output TalkMessageListDto
	 *
	 */
	@RequestMapping(value = "talkMessageListDto", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<TalkMessageListDTO> talkMessageListDto(@RequestBody PageParamDTO pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<TalkMessageListDTO> responseDto = new ResponseDTO<TalkMessageListDTO>();
		TalkMessageListDTO talkMessageListDto = new TalkMessageListDTO();

		return responseDto;
	}


}
