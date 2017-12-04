package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.hospital.DeviceDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.healthDataService.service.DeviceService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by sunxiao on 2017/6/20.
 */
@Controller
@RequestMapping(value = "device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    /**
     * 获取设备列表
     * @param searchValue
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/searchDevice",method = {RequestMethod.POST, RequestMethod.GET})
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
            deviceService.findDeviceList(searchValue, page);
            response.setResult(StatusConstant.SUCCESS);
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        response.setResponseData(page);
        return response;
    }

    /**
     * 设备统计数据
     * @return
     */
    @RequestMapping(value="/statisticsDevice",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO statisticsDevice() {
        ResponseDTO response = new ResponseDTO();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = deviceService.getStatisticsDevice();
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonArray);
        return response;
    }

    /**
     * 设备的增删改
     * @param deviceDTO
     * @return
     */
    @RequestMapping(value="/operDevice",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO operDevice(@RequestBody DeviceDTO deviceDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            deviceService.operDevice(deviceDTO);
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            response.setResult(StatusConstant.FAILURE);
            response.setErrorInfo(StatusConstant.FAILURE);
            e.printStackTrace();
        }
        return response;
    }
}
