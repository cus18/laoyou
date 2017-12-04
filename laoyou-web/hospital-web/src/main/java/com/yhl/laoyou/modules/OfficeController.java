package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.hospital.DeviceDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.healthDataService.service.DeviceService;
import com.yhl.laoyou.modules.hospitalService.service.OfficeService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Controller
@RequestMapping(value = "office")
public class  OfficeController {

    @Autowired
    OfficeService officeService;

    @Autowired
    UserDao userDao;

    /**
     * 获取机构列表
     * @param searchValue
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/searchOffice",method = {RequestMethod.POST, RequestMethod.GET})
    public
//    @LoginRequired
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
        if(pageSize.equals("-1")||pageNo==null||pageSize==null){
            page = new Page();
        }else{
            page = new Page(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        }
        try {
            page=officeService.getOfficeList(searchValue,page);
            response.setResult(StatusConstant.SUCCESS);
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        response.setResponseData(page);
        return response;
    }

    @RequestMapping(value="/addOffice",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO addOffice(@RequestBody Office office, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            User user= UserService.getUser(request);
            office.setCreate_by(user.getId());
            office.setUpdate_by(user.getId());
            Integer result=officeService.insertOffice(office);
            response.setResponseData(result);
//            if(result==9){
//                response.setResponseData("手机号已存在");
//            }
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/updateOffice",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO updateOffice(@RequestBody Office office, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            User user= UserService.getUser(request);
            office.setUpdate_by(user.getId());
            Integer result=officeService.updateOffice(office);
            response.setResponseData(result);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/deleteOffice",method = {RequestMethod.POST, RequestMethod.GET})
    public
//    @LoginRequired
    @ResponseBody
    ResponseDTO deleteOffice(@RequestBody Office office, HttpServletRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            User user= UserService.getUser(request);
            office.setUpdate_by(user.getId());

            Integer result=officeService.deleteOffice(office);
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

    @RequestMapping(value="/getOffice",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO getOffice(@RequestParam String ID) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setResponseData(officeService.getOfficeByID(ID));
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }


}
