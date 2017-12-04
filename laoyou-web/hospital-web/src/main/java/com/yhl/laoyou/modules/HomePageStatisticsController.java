package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.modules.healthDataService.service.HomePageStatisticsService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页数据统计
 * Created by sunxiao on 2017/7/4.
 */
@Controller
@RequestMapping(value = "homePageStatistics")
public class HomePageStatisticsController {

    @Autowired
    HomePageStatisticsService homePageStatisticsService;

    /**
     * 会员统计
     * @return
     */
    @RequestMapping(value="/memberStatistics",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO memberStatistics() {
        ResponseDTO response = new ResponseDTO();
        JSONObject jsonObject = null;
        try {
            jsonObject = homePageStatisticsService.memberStatistics();
        }catch (Exception e){
            e.printStackTrace();
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonObject);
        return response;
    }

    /**
     * 糖尿病会员统计
     * @return
     */
    @RequestMapping(value="/diabeticStatistics",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO diabeticStatistics() {
        ResponseDTO response = new ResponseDTO();
        JSONObject jsonObject = null;
        try {

            jsonObject = homePageStatisticsService.diabeticStatistics();
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonObject);
        return response;
    }

    /**
     * 高血压会员统计
     * @return
     */
    @RequestMapping(value="/hypertensiveStatistics",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO hypertensiveStatistics() {
        ResponseDTO response = new ResponseDTO();
        JSONObject jsonObject = null;
        try {
            jsonObject = homePageStatisticsService.hypertensiveStatistics();
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonObject);
        return response;
    }

    /**
     * 医生统计
     * @return
     */
    @RequestMapping(value="/doctorStatistics",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO doctorStatistics() {
        ResponseDTO response = new ResponseDTO();
        JSONObject jsonObject = null;
        try {
            jsonObject = homePageStatisticsService.doctorStatistics();
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonObject);
        return response;
    }

    /**
     * 护士统计
     * @return
     */
    @RequestMapping(value="/nurseStatistics",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO nurseStatistics() {
        ResponseDTO response = new ResponseDTO();
        JSONObject jsonObject = null;
        try {
            jsonObject = homePageStatisticsService.nurseStatistics();
        }catch (Exception e){
            response.setResult(StatusConstant.FAILURE);
        }
        response.setResult(StatusConstant.SUCCESS);
        response.setResponseData(jsonObject);
        return response;
    }
}
