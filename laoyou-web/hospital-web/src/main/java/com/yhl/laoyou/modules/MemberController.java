package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.BasicInfoDTO;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.healthDataService.service.DoctorService;
import com.yhl.laoyou.modules.healthService.service.HealthArchive;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于账户管理
 *
 * @author 张博
 * @date 2017-5-5
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {

    private static Lock lock = new ReentrantLock();

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    @Autowired
    private HealthArchive healthArchive;

    @Autowired
    private DoctorService doctorService;

    /**
     * 获取会员档案
     */
    @RequestMapping(value = "/getMemberInfoList", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getMemberInfoList(HttpServletRequest request,
                                  @RequestParam(required = true) Integer pageNo,
                                  @RequestParam(required = true) Integer pageSize,
                                  @RequestParam(required = true) String searchValue) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        Page page = new Page(pageNo, pageSize);
        page = healthArchive.getElderBasicInfoList(UserService.getUser(request).getSysHospitalUserDTO().getId(), page, searchValue);
        responseDTO.setResponseData(page);
        responseDTO.setResult(StatusConstant.SUCCESS);
        return responseDTO;
    }


    /**
     * 获取会员档案
     */
    @RequestMapping(value = "/getMemberInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getMemberInfo(@RequestParam String id) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        BasicInfoDTO basicInfoDTO = healthArchive.getElderBasicInfo(id);
        responseDTO.setResponseData(basicInfoDTO);
        responseDTO.setResult(StatusConstant.SUCCESS);
        return responseDTO;
    }

    /**
     * 批量上传会员信息;
     */
    @RequestMapping(value = "/uploadMemberFile", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO uploadMemberFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            System.out.println("fileName：" + file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("/")
                    + "/uploadTempDirectory/" + new Date().getTime() + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            healthArchive.batchAddElderBasicInfo(newFile, request);
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setResult(StatusConstant.FAILURE);
            responseDTO.setErrorInfo("会员信息不完整");
            return responseDTO;
        }
    }


    /**
     * 单个添加用户
     */
    @RequestMapping(value = "/addMember", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO addMember(@RequestBody BasicInfoDTO basicInfoDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        try {
            healthArchive.addElderBasicInfo(basicInfoDTO, request);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
            response.setErrorInfo("此手机号已存在");
        }
        return response;
    }

    /**
     * 更新用户
     */
    @RequestMapping(value = "/updateMember", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO updateMember(@RequestBody BasicInfoDTO basicInfoDTO) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        try {
            healthArchive.updateElderBasicInfo(basicInfoDTO);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }


    /**
     * 删除用户
     */
    @RequestMapping(value = "/delMember", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO delMember(@RequestParam("id") String id, HttpServletRequest request) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        try {
            healthArchive.delElderBasicInfo(id, request);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }


    /**
     * 指派医生
     */
    @RequestMapping(value = "/assignedDoctor", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO assignedDoctor(@RequestBody HashMap<String,Object> param,HttpServletRequest request) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        try {
            String doctorEasemobID = (String) param.get("id");
            String doctorName = (String) param.get("doctorName");
            String elderID = (String) param.get("elderID");
            String type = String.valueOf(param.get("type"));
            String result=doctorService.assignedDoctor(doctorEasemobID, doctorName, elderID,type);
            if(result==null||result.equals("403")){
                response.setResult(StatusConstant.PARAM_ERROR);
            }else {
                response.setResult(StatusConstant.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }

}
