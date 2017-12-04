package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.utils.CheckParam;
import com.yhl.laoyou.modules.healthService.service.HealthServicePackage;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 关于账户管理
 * @author frank
 * @date 2015-10-14
 */
@Controller
@RequestMapping(value = "health/healthService/")
public class HealthServiceController {

	@Autowired
	private HealthServicePackage healthServicePackage;

	/**
	 * 获取当前用户正在进行的服务套餐列表
	 *
	 *  input PageParamDto
	 *
	 *  output List<HealthServicePackageDto>
	 *
	 */
	@RequestMapping(value = "packageList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<HealthServicePackageDTO>> healthServicePackageList(@RequestBody PageParamDTO<MemberDTO> pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO<List<HealthServicePackageDTO>> responseDto = new ResponseDTO<>();
		try{
			CheckParam.checkPageParamDto(pageParamDto);
			responseDto.setResponseData(healthServicePackage.getHealthServicePackageList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo(StatusConstant.PARAM_ERROR);
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	/**
	 * 获取当前用户正在进行的某个服务套餐的详细信息
	 *
	 *  input PageParamDto
	 *
	 *  output List<HealthServicePackageDto>
	 *
	 */
	@RequestMapping(value = "package", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<HealthServicePackageDTO> healthServicePackage(@RequestParam(required = true) String healthServicePackageId,
                                                              @RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<HealthServicePackageDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthServicePackage.getOnGoingHealthServicePackage(healthServicePackageId));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getOnGoingHealthServicePackage is failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	/**
	 * 获取当前所有可为用户创建的服务套餐列表
	 *
	 *  input PageParamDto
	 *
	 *  output List<HealthServicePackageDto>
	 *
	 */
	@RequestMapping(value = "templateList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<HealthServicePackageTemplateDTO>> healthServicePackageTemplateList(@RequestBody PageParamDTO pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);

		ResponseDTO<List<HealthServicePackageTemplateDTO>> responseDto = new ResponseDTO<>();
		try{
			CheckParam.checkPageParamDto(pageParamDto);
			responseDto.setResponseData(healthServicePackage.getHealthServicePackageTemplateList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("pageParamDto is not valid");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;

	}

	/**
	 * 获取服务套餐模板的服务协议详情
	 *
	 *  input serviceTemplateId
	 *
	 *  output HealthServicePackageTemplateDto
	 *
	 */
	@RequestMapping(value = "template", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<HealthServicePackageTemplateDTO> healthServicePackageTemplate(@RequestParam(required = true) String healthServicePackageTemplateId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<HealthServicePackageTemplateDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthServicePackage.getHealthServicePackageTemplateDetail(healthServicePackageTemplateId));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthServicePackageTemplateDetail failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	/**
	 * 为用户创建新的服务套餐
	 *
	 *  input serviceTemplateId
	 *
	 *  output HealthServicePackageTemplateDto
	 *
	 */
	@RequestMapping(value = "package/create", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<HealthServicePackageDTO> createHealthServicePackage(@RequestBody HealthServicePackageDTO healthServicePackageDto,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<HealthServicePackageDTO> responseDto = new ResponseDTO<>();
		try{
			User user= UserService.getUser(request);
			responseDto.setResponseData(healthServicePackage.createHealthServicePackage(healthServicePackageDto,user.getId()));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthServicePackageTemplateDetail failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}


	@RequestMapping(value = "elderContactInfo", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<MemberDTO> elderContactInfo(@RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<MemberDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthServicePackage.getElderContactInfo(elderId));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getElderContactInfo failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

}
