package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.*;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.modules.healthService.service.HealthArchive;
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
@RequestMapping(value = "health/healthArchive/")
public class HealthArchiveController {

	@Autowired
	private HealthArchive healthArchive;

	@RequestMapping(value = "basicInfo", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<BasicInfoDTO> healthArchiveBasicInfo(@RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<BasicInfoDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getElderBasicInfo(elderId));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthArchiveBascInfo failure"+e);
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	@RequestMapping(value = "physicalExaminationList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<PhysicalExaminationDTO>> healthArchivePhysicalExaminationList(@RequestBody PageParamDTO<MemberDTO> pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<PhysicalExaminationDTO>> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getPhysicalExaminationList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthArchivePhysicalExamination failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	@RequestMapping(value = "physicalExaminationTemplateList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<PhysicalExaminationTemplateDTO>> healthArchivePhysicalExaminationTemplateList(@RequestBody PageParamDTO pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<PhysicalExaminationTemplateDTO>> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getPhysicalExaminationTemplateList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthArchivePhysicalExamination failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	/**
	 *  获取当前管理用户的某次体检记录详情
	 *
	 * 	input pageParamDto, requestData(elderId/physicalExaminationId)
	 *
	 *  output List<PhysicalExaminationDto>
	 *
	 */
	@RequestMapping(value = "physicalExamination", method = {RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<PhysicalExaminationDTO> healthArchivePhysicalExamination(@RequestParam(required = true) String physicalExaminationId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<PhysicalExaminationDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getPhysicalExamination(physicalExaminationId));
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
	 *  为当前管理的用户创建新的体检记录
	 *
	 * 	input PhysicalExaminationDto, elderId
	 *
	 *  output List<PhysicalExaminationDto>
	 *
	 */
	@RequestMapping(value = "physicalExamination/create", method = {RequestMethod.POST})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<PhysicalExaminationDTO> createPhysicalExamination(@RequestBody PhysicalExaminationDTO physicalExaminationDTO,
																  HttpServletRequest request) {

		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<PhysicalExaminationDTO> responseDto = new ResponseDTO<>();
		try{
			User user = UserService.getUser(request);
			physicalExaminationDTO.setProviderId(user.getSysPractitionerUser().getId());
			physicalExaminationDTO.setProviderType(user.getSysPractitionerUser().getTitle());
			physicalExaminationDTO.setProviderName(user.getName());
			responseDto.setResponseData(healthArchive.createPhysicalExamination(physicalExaminationDTO));
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
	 *  获取当前管理用户参加的健康评估信息列表
	 *
	 *	input pageParamDto, elderId
	 *
	 *  output String
	 *  JSON数据被转换成String类型
	 *
	 */
	@RequestMapping(value = "healthAssessmentList", method = {RequestMethod.POST})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<HealthAssessmentDTO>> healthAssessmentList(@RequestBody PageParamDTO<MemberDTO> pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<HealthAssessmentDTO>> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getHealthAssessmentList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("getHealthAssessmentList failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

	/**
	 *  获取当前管理用户的某次健康评估信息详情
	 *
	 *	input healthAssessmentId, elderId
	 *
	 *  output HealthAssessmentDto
	 *
	 */
	@RequestMapping(value = "saveHealthAssessmentAnswer", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<HealthAssessmentDTO> saveHealthAssessmentAnswer(@RequestBody HealthAssessmentDTO healthAssessmentAnswer,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO responseDto = new ResponseDTO<>();
		try{
			User user = UserService.getUser(request);
			healthAssessmentAnswer.setProviderId(user.getSysPractitionerUser().getId());
			healthAssessmentAnswer.setProviderType(user.getSysPractitionerUser().getTitle());
			healthAssessmentAnswer.setProviderName(user.getName());
			healthArchive.createHealthAssessment(healthAssessmentAnswer);
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
	 *  获取当前管理用户的某次健康评估信息详情
	 *
	 *	input healthAssessmentId, elderId
	 *
	 *  output HealthAssessmentDto
	 *
	 */
	@RequestMapping(value = "healthAssessment", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<HealthAssessmentDTO> healthAssessment(@RequestParam(required = true) String healthAssessmentId,
													  @RequestParam(required = true) String keyId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<HealthAssessmentDTO> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.getHealthAssessment(healthAssessmentId,keyId));
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
	 *  获取健康评估信息模板列表
	 *
	 *	input PageParamDto
	 *
	 *  output HealthAssessmentTemplateDto
	 *
	 */
	@RequestMapping(value = "templateList", method = {RequestMethod.POST})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<HealthAssessmentTemplateDTO>> healthAssessmentTemplateList(@RequestBody PageParamDTO pageParamDto) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List<HealthAssessmentTemplateDTO>> responseDto = new ResponseDTO<>();
		try{
			responseDto.setResponseData(healthArchive.GetHealthArchiveHealthAssessmentTemplateList(pageParamDto));
			responseDto.setResult(StatusConstant.SUCCESS);
		}
		catch (Exception e)
		{
			responseDto.setErrorInfo("GetHealthArchiveHealthAssessmentTemplateList failure");
			responseDto.setResult(StatusConstant.FAILURE);
		}
		return responseDto;
	}

}
