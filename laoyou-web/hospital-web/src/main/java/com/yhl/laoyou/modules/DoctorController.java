package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.hospital.DoctorDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.healthDataService.service.DoctorService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 关于账户管理
 *
 * @author 张博
 * @date 2017-5-5
 */
@Controller
@RequestMapping(value = "doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    /**
     * 获取医生列表
     */
    @RequestMapping(value = "/getDoctorList", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getDoctorList(HttpServletRequest request,
                              @RequestParam(required = true) Integer pageNo,
                              @RequestParam(required = true) Integer pageSize,
                              @RequestParam(required = true) String searchValue,
                              @RequestParam(required = true) String type) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        try {
            User user = UserService.getUser(request);
            Page page = new Page(pageNo, pageSize);
            response.setResponseData(doctorService.getDoctorList(request, page, searchValue, type));
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }

    /**
     * 创建医护人员
     *
     * @param doctorDTO
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO create(@RequestBody DoctorDTO doctorDTO, HttpServletRequest request)  {
        ResponseDTO response = new ResponseDTO();
        try {
            doctorService.addDoctor(doctorDTO, request);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
            response.setErrorInfo("手机号已存在");
        }
        return response;
    }

    /**
     * 上传医护人员
     *
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadDoctorFile", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO uploadDoctorFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
        ResponseDTO responseDTO=new ResponseDTO();
        try {
            System.out.println("fileName：" + file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("/")
                    + "/uploadTempDirectory/" + new Date().getTime() + file.getOriginalFilename();
            File newFile = new File(path);
            file.transferTo(newFile);
            doctorService.batchAddDoctor(newFile, request);
            responseDTO.setResult(StatusConstant.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setResult(StatusConstant.FAILURE);
            responseDTO.setErrorInfo("医生信息不全");
            return responseDTO;
        }
    }

    /**
     * 获取医生详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDoctor", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getDoctor(@RequestParam(required = true) String id) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setResponseData(doctorService.getDoctor(id));
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }

    /**
     * 更新医生信息
     * @param doctorDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateDoctor", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO updateDoctor(@RequestBody(required = true) DoctorDTO doctorDTO) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try {
            doctorService.updateDoctor(doctorDTO);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }

    /**
     * 删除医生
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delDoctor", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO delDoctor(@RequestParam(required = true) String id) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try {
            doctorService.delDoctor(id);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.PARAM_ERROR);
        }
        return response;
    }
}
