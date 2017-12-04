package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.hospitalService.service.OfficeService;
import com.yhl.laoyou.modules.hospitalService.service.UserManageService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.dao.SysHospitalUserDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Controller
@RequestMapping(value = "userManage")
public class UserManageController {

    @Autowired
    UserManageService userManageService;


    /**
     * 获取机构列表
     * @param searchValue
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/searchUser",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO search(@RequestParam(required = true) String searchValue,
                       @RequestParam(required = true) String pageNo,
                       @RequestParam(required = true) String pageSize) {
        ResponseDTO response = new ResponseDTO();
        Page page = null;
        try {
            searchValue = URLDecoder.decode(searchValue,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(pageNo==null||pageSize==null){
            page = new Page();
        }else{
            page = new Page(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        }
        try {
            page=userManageService.getUserList(searchValue,page);
            response.setResult(StatusConstant.SUCCESS);
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        response.setResponseData(page);
        return response;
    }

    @RequestMapping(value="/addUser",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO addOffice(@RequestBody SysHospitalUserDTO sysHospitalUserDTO, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
//            User user= UserService.getUser(request);
//            sysHospitalUserDTO.setSysOfficeID(user.getSysHospitalUserDTO().getSysOfficeID());
            Integer result=userManageService.addUser(sysHospitalUserDTO,request);
            response.setResponseData(result);
            if(result==9){
                response.setResponseData("手机号已存在");
            }
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/updateUser",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO updateOffice(@RequestBody SysHospitalUserDTO sysHospitalUserDTO, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            Integer result=userManageService.updateUser(sysHospitalUserDTO);
            response.setResponseData(result);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/deleteUser",method = {RequestMethod.POST, RequestMethod.GET})
    public
//    @LoginRequired
    @ResponseBody
    ResponseDTO deleteOffice(@RequestBody SysHospitalUserDTO sysHospitalUserDTO, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            Integer result=userManageService.deleteUser(sysHospitalUserDTO.getSysUserID());
            if(result==1) {
                response.setResponseData(result);
                response.setResult(StatusConstant.SUCCESS);
            }else{
                response.setResponseData(result);
                response.setResult(StatusConstant.FAILURE);
            }
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/getUser",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getOffice(@RequestParam String ID) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setResponseData(userManageService.getUserByID(ID));
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }


}
