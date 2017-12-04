package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationAllPlanDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanTimingDTO;
import com.yhl.laoyou.modules.healthService.service.MedicationPlanService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 服药干预
 *
 * @author 张博
 * @date 2017年5月25日
 */
@Controller
@RequestMapping(value = "health/")
public class MedicationPlanController {

    private static Lock lock = new ReentrantLock();

    @Autowired
    private MedicationPlanService medicationPlanService;

    /**
     * 用户的所有的服药干预
     *
     * @return
     */
    @RequestMapping(value = "getMedicationPlan", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<MedicationAllPlanDTO> getMedicationPlan(@RequestParam(required = true) String elderUserID) throws  Exception {
        MedicationAllPlanDTO medicationAllPlanDTO = new MedicationAllPlanDTO();
        String elderID = elderUserID;
        medicationAllPlanDTO.setComplete(medicationPlanService.getCompleteMedicationPlanByUser(elderID));
        medicationAllPlanDTO.setUncompleted(medicationPlanService.getUncompletedMedicationPlanByUser(elderID));
        ResponseDTO<MedicationAllPlanDTO> ResponseDTO = new ResponseDTO<>();
        ResponseDTO.setResponseData(medicationAllPlanDTO);
        ResponseDTO.setResult(StatusConstant.SUCCESS);
        ResponseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return ResponseDTO;
    }

    /**
     * 新建用药干预
     * @param medicationPlanDTO
     * @return
     */
    @RequestMapping(value = "insertMedicationPlan", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO insertMedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO) {
        medicationPlanService.insertMedicationPlan(medicationPlanDTO);
        ResponseDTO ResponseDTO = new ResponseDTO<>();
        ResponseDTO.setResult(StatusConstant.SUCCESS);
        ResponseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return ResponseDTO;
    }

    /**
     * 更新用药干预
     * @param medicationPlanDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "updateMedicationPlan", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO updateMedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO, HttpServletRequest request) {
        medicationPlanService.updateMedicationPlan(medicationPlanDTO);
        ResponseDTO ResponseDTO = new ResponseDTO<>();
        ResponseDTO.setResult(StatusConstant.SUCCESS);
        ResponseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return ResponseDTO;
    }

    /**
     * 删除用药干预
     * @param medicationPlanDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteMedicationPlan", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO deleteMedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO, HttpServletRequest request) {
        medicationPlanService.deleteMedicationPlan(medicationPlanDTO);
        ResponseDTO ResponseDTO = new ResponseDTO<>();
        ResponseDTO.setResult(StatusConstant.SUCCESS);
        ResponseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return ResponseDTO;
    }

    /**
     *  更新用药状态
     * @param medicationPlanDTO
     * @return
     */
    @RequestMapping(value = "updateMedicationPlanStatus", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO updateMedicationPlanStatus(@RequestBody MedicationPlanDTO medicationPlanDTO) {
        medicationPlanService.updateMedicationPlanStatus(medicationPlanDTO.getId(),medicationPlanDTO.getStatus());
        ResponseDTO ResponseDTO = new ResponseDTO<>();
        ResponseDTO.setResult(StatusConstant.SUCCESS);
        ResponseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return ResponseDTO;
    }

    /**
     *  获取一条用药干预记录
     * @param mDTO
     * @return
     */
    @RequestMapping(value = "getMedicationPlanByID", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<MedicationPlanDTO> getMedicationPlanByID(@RequestBody MedicationPlanDTO mDTO) {
        MedicationPlanDTO medicationPlanDTO=medicationPlanService.getMedicationPlanByID(mDTO.getId());
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setResponseData(medicationPlanDTO);
        responseDTO.setResult(StatusConstant.SUCCESS);
        responseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return responseDTO;
    }

    /**
     *  获取定时执行任务
     * @param mDTO
     * @return
     */
    @RequestMapping(value = "getMedicationTimingPlanByID", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<MedicationPlanTimingDTO> getMedicationTimingPlanByID(@RequestBody MedicationPlanTimingDTO mDTO) {
        MedicationPlanTimingDTO medicationPlanDTO=medicationPlanService.getMedicationPlanTimingByID(mDTO.getId());
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setResponseData(medicationPlanDTO);
        responseDTO.setResult(StatusConstant.SUCCESS);
        responseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return responseDTO;
    }


    /**
     *  获取定时执行任务
     * @param mDTO
     * @return
     */
    @RequestMapping(value = "getMedicationPlanTimingByElderUserID", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO< List<MedicationPlanTimingDTO>> getMedicationPlanTimingByElderUserID(@RequestBody MedicationPlanDTO mDTO) {
        List<MedicationPlanTimingDTO> medicationPlanDTO=medicationPlanService.getMedicationPlanTimingByElderUserID(mDTO.getElderID(),mDTO.getStartTime(),mDTO.getEndTime());
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setResponseData(medicationPlanDTO);
        responseDTO.setResult(StatusConstant.SUCCESS);
        responseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return responseDTO;
    }

    /**
     *  分页获取用户用药干预
     * @param mDTO
     * @return
     */
    @RequestMapping(value = "getAllMedicationPlanByElderUser", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO< List<MedicationPlanTimingDTO>> getAllMedicationPlanByElderUser(@RequestBody MedicationPlanDTO mDTO) {
        List<MedicationPlanDTO> medicationPlanDTO=medicationPlanService.getAllMedicationPlanByUser(mDTO.getElderID());
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.setResponseData(medicationPlanDTO);
        responseDTO.setResult(StatusConstant.SUCCESS);
        responseDTO.setErrorInfo(StatusConstant.Medication_ERROR);
        return responseDTO;
    }

}
