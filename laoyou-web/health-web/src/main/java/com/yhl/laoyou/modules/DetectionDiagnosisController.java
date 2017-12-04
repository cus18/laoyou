package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dataSource.DataSourceInstances;
import com.yhl.laoyou.common.dataSource.DataSourceSwitch;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.detection.DetectionDTO;
import com.yhl.laoyou.common.dto.practitioner.test.TestReportDTO;
import com.yhl.laoyou.common.dto.practitioner.treatment.TreatmentDTO;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.modules.healthDataService.service.HealthDataService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.entity.healthData.CommonData;
import com.yhl.laoyou.modules.sys.entity.healthData.ControlTargetData;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 检测与诊疗模块
 * @author frank
 * @date 2015-10-14
 */
@Controller
@RequestMapping(value = "health/detectionDiagnose/")
public class DetectionDiagnosisController {

	@Autowired
	HealthDataService healthDataService;
	/**
	 * 获取当前用户的远程检测数据
	 *
	 *  input detectionDTO,detectionStartTime,detectionEndTime,elderId
	 *
	 *  output ResponseDTO<DetectionDTO>
	 *
	 */
	@RequestMapping(value = "detection", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<DetectionDTO> detection(@RequestParam(required = true) String detectionType,
										@RequestParam(required = true) String detectionDateType,
										@RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<DetectionDTO> responseDTO = healthDataService.getHealthDataFromMongo(detectionType,detectionDateType,elderId);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 给当前用户录入的新的检测数据
	 *
	 *  input DetectionDTO
	 *
	 *  output ResponseDTO<DetectionDTO>
	 *
	 */
	@RequestMapping(value = "createDetection", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<DetectionDTO> createDetection(@RequestBody DetectionDTO<CommonData> detectionDTO) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<DetectionDTO> responseDTO = new ResponseDTO<DetectionDTO>();
		try {
			healthDataService.saveHealthDataToMongo(detectionDTO);
			responseDTO.setResult(StatusConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO.setResult(StatusConstant.FAILURE);
			responseDTO.setErrorInfo("测量时间不能晚于当前时间！");
		}
		return responseDTO;
	}

	/**
	 * 给当前用户录入的新的检测数据
	 *
	 *  input DetectionDTO
	 *
	 *  output ResponseDTO<DetectionDTO>
	 *
	 */
	@RequestMapping(value = "controlTarget", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<DetectionDTO> controlTarget(@RequestBody DetectionDTO<ControlTargetData> detectionDTO) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<DetectionDTO> responseDTO = new ResponseDTO<DetectionDTO>();
		healthDataService.saveControlTargetToMongo(detectionDTO);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 * 给当前用户录入的新的检测数据
	 *
	 *  input DetectionDTO
	 *
	 *  output ResponseDTO<DetectionDTO>
	 *
	 */
	@RequestMapping(value = "getControlTarget", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<DetectionDTO> getControlTarget(@RequestParam(required = true) String elderId,
											   @RequestParam(required = true) String detectionType) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<DetectionDTO> responseDTO = new ResponseDTO<DetectionDTO>();
		DetectionDTO detectionDTO = healthDataService.getControlTargetFromMongo(elderId,detectionType);
		responseDTO.setResponseData(detectionDTO);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 *  获取当前用户的化验报告
	 *
	 *  input testReportStartDate,testReportEndDate,elderId
	 *
	 *  output ResponseDTO<List<TestReportDTO>>
	 *
	 */
	@RequestMapping(value = "testReport", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List> testReport(@RequestParam(required = true) String startDate,
								 @RequestParam(required = true) String endDate,
								 @RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List> responseDTO = healthDataService.getTestReport(elderId, startDate, endDate);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 *  创建用户的化验报告
	 *
	 *  input TestReportDTO,elderId
	 *
	 *  output ResponseDTO<List<TestReportDTO>>
	 *
	 */
	@RequestMapping(value = "createTestReport", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<TestReportDTO> createTestReport(@RequestBody TestReportDTO testReportDTO,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<TestReportDTO> responseDTO = new ResponseDTO<TestReportDTO>();
		try {
			User user = UserService.getUser(request);
			testReportDTO.setProviderId(user.getSysPractitionerUser().getId());
			testReportDTO.setProviderName(user.getName());
			healthDataService.createTestReport(testReportDTO);
			responseDTO.setResult(StatusConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO.setResult(StatusConstant.FAILURE);
		}
		return responseDTO;
	}

	/**
	 *  获取当前用户某个时段的诊疗记录
	 *
	 *  input treatmentStartDate,treatmentEndDate,elderId
	 *
	 *  output ResponseDTO<List<TestReportDTO>>
	 *
	 */
	@RequestMapping(value = "treatment", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List> treatment(@RequestParam(required = true) String startDate,
								@RequestParam(required = true) String endDate,
								@RequestParam(required = true) String elderId) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<List> responseDTO = healthDataService.getTreatmentRecord(elderId, startDate, endDate);
		responseDTO.setResult(StatusConstant.SUCCESS);
		return responseDTO;
	}

	/**
	 *  为当前用户创建新的诊疗记录
	 *
	 *  input TreatmentDTO,elderId
	 *
	 *  output ResponseDTO<List<TestReportDTO>>
	 *
	 */
	@RequestMapping(value = "createTreatment", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<TreatmentDTO> createTreatment(@RequestBody TreatmentDTO treatmentDTO,HttpServletRequest request) {
		DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);
		ResponseDTO<TreatmentDTO> responseDTO = new ResponseDTO<TreatmentDTO>();
		try {
			User user = UserService.getUser(request);
			treatmentDTO.setProviderId(user.getSysPractitionerUser().getId());
			treatmentDTO.setProviderName(user.getName());
			healthDataService.createTreatment(treatmentDTO);
			responseDTO.setResult(StatusConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO.setResult(StatusConstant.FAILURE);
		}
		return responseDTO;
	}

}
