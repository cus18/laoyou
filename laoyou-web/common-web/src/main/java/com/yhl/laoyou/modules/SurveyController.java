/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules;

import com.alibaba.druid.support.json.JSONUtils;
import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.survey.*;
import com.yhl.laoyou.common.utils.DateUtils;
import com.yhl.laoyou.common.utils.HttpUtils;
import com.yhl.laoyou.common.web.BaseController;
import com.yhl.laoyou.modules.myService.service.SurveyService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 工具 Controller
 *
 * @author ThinkGem
 * @version 2013-10-17
 */
@Controller
@RequestMapping(value = "survey")
public class SurveyController extends BaseController {

    @Autowired
    private SurveyService surveyService;

    private String appkey = "1833de2d5777022e";

    @RequestMapping(value="/list",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<SurveyDTO>> allQuestions(HttpServletRequest request) {
        ResponseDTO responseDto=new ResponseDTO<>();
        User user= UserService.getUser(request);
        List<SurveyDTO> surveyDTOList = surveyService.getAllSurveyQuestions();
        responseDto.setResult(StatusConstant.SUCCESS);
        responseDto.setResponseData(surveyDTOList);
        return responseDto;
    }

    //提交和更新答题
    @RequestMapping(value="/answer",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO answer(@RequestBody FrontAnswerDTO frontAnswerDTO, HttpServletRequest request) {

        ResponseDTO responseDto=new ResponseDTO();
        User user= UserService.getUser(request);

        AnswerDTO answerDTO = this.transferFrontToBackAnswerDto(frontAnswerDTO);

        try
        {
            synchronized (this){
                surveyService.createOrUpdateAnswer(answerDTO, user);
            }
            responseDto.setResult(StatusConstant.SUCCESS);
        }
        catch (Exception e)
        {
            if(e.getMessage().equals("already"))
            {
                responseDto.setResult("already");
            }
            else
            {
                responseDto.setResult(StatusConstant.FAILURE);
            }
        }

        return responseDto;
    }

    //预览答题或者获取某个题目的答案
    @RequestMapping(value="/getAnswer",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<FrontAnswerDTO>> getAnswer(@RequestBody FrontAnswerDTO frontAnswerDTO, HttpServletRequest request) {
        ResponseDTO responseDto=new ResponseDTO<>();
        User user= UserService.getUser(request);
        AnswerDTO answerDTO = this.transferFrontToBackAnswerDto(frontAnswerDTO);

        List<AnswerDTO> answerDTOList = surveyService.findSurveyAnswer(answerDTO);

        List<FrontAnswerDTO> frontAnswerDTOList = new ArrayList<>();
        for(AnswerDTO answerDTO1 : answerDTOList)
        {
            frontAnswerDTOList.add(this.transferBackToFrontAnswerDto(answerDTO1));
        }

        responseDto.setResponseData(frontAnswerDTOList);
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }

    //获取省数据列表
    @RequestMapping(value="/province",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<AreaDTO>> province(HttpServletRequest request) {
        ResponseDTO responseDto=new ResponseDTO<>();
        User user= UserService.getUser(request);
        String URL = "http://api.jisuapi.com/area/province?appkey=" + appkey;
        String responseValue = HttpUtils.doGet(URL);

        List<AreaDTO> areaDTOList  = (List<AreaDTO>) ((HashMap<String,Object>) JSONUtils.parse(responseValue)).get("result");

        responseDto.setResponseData(areaDTOList);
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }

    //获取城市数据列表
    @RequestMapping(value="/area",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<AreaDTO>> area(@RequestParam String id, HttpServletRequest request) {
        ResponseDTO responseDto=new ResponseDTO<>();
        User user= UserService.getUser(request);
        String URL = "http://api.jisuapi.com/area/city?parentid="+ id +"&appkey=" + appkey;
        String responseValue = HttpUtils.doGet(URL);

        List<AreaDTO> areaDTOList  = (List<AreaDTO>) ((HashMap<String,Object>) JSONUtils.parse(responseValue)).get("result");

        responseDto.setResponseData(areaDTOList);
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }

    private AnswerDTO transferFrontToBackAnswerDto(FrontAnswerDTO frontAnswerDTO)
    {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(frontAnswerDTO.getId());
        answerDTO.setWorkerPhone(frontAnswerDTO.getWorker_phone());
        answerDTO.setWorkerName(frontAnswerDTO.getWorker_name());
        answerDTO.setElderIdentityNum(frontAnswerDTO.getElder_identity_num());
        answerDTO.setQuestionAnswer(frontAnswerDTO.getQuestion_answer());
        answerDTO.setQuestionName(frontAnswerDTO.getQuestion_name());
        answerDTO.setQuestionId(Integer.parseInt(frontAnswerDTO.getQuestion_id()));
        answerDTO.setUpdateDate(frontAnswerDTO.getUpdate_date());
        return  answerDTO;
    }

    private FrontAnswerDTO transferBackToFrontAnswerDto(AnswerDTO answerDTO)
    {
        FrontAnswerDTO frontAnswerDTO = new FrontAnswerDTO();
        frontAnswerDTO.setId(answerDTO.getId());
        frontAnswerDTO.setWorker_phone(answerDTO.getWorkerPhone());
        frontAnswerDTO.setWorker_name(answerDTO.getWorkerName());
        frontAnswerDTO.setElder_identity_num(answerDTO.getElderIdentityNum());
        frontAnswerDTO.setQuestion_answer(answerDTO.getQuestionAnswer());
        frontAnswerDTO.setQuestion_name(answerDTO.getQuestionName());
        frontAnswerDTO.setQuestion_id(String.valueOf(answerDTO.getQuestionId()));
        frontAnswerDTO.setUpdate_date(answerDTO.getUpdateDate());
        return frontAnswerDTO;
    }



    //后台系统接口

    //单因素统计
    @RequestMapping(value="/singleStatistic",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<SingleStatisticDTO>> singleStatistic(@RequestBody SurveyDTO surveyDTO, HttpServletRequest request) {

        ResponseDTO responseDto=new ResponseDTO();
        try{
            List<SingleStatisticDTO> singleStatisticDTOList = surveyService.singleStatistic(surveyDTO);
            responseDto.setResult(StatusConstant.SUCCESS);
            responseDto.setResponseData(singleStatisticDTOList);
        }catch (Exception e){
            e.printStackTrace();
            responseDto.setErrorInfo("search error!");
        }
        return responseDto;
    }

    //交叉分析
    //independentVariableList为自变量列表
    //dependentVariableList为因变量列表
    @RequestMapping(value="/crossStatistic",method = {RequestMethod.POST, RequestMethod.GET})
    //@LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<List<List>>> crossStatistic(@RequestBody CrossParam crossParam,
                                                         HttpServletRequest request) {

        ResponseDTO responseDto=new ResponseDTO();
        try {
            List<List<List>> crossStatisticDTOList = new ArrayList<>();
            long from = System.currentTimeMillis();
            System.out.println("==========================="+ DateUtils.DateToStr(new Date()));
            crossStatisticDTOList = surveyService.crossStatistic(crossParam.getIndependentVariableList(),crossParam.getDependentVariableList());
            responseDto.setResult(StatusConstant.SUCCESS);
            responseDto.setResponseData(crossStatisticDTOList);
            long to = System.currentTimeMillis();
            System.out.println("==========================="+ DateUtils.DateToStr(new Date()));
            System.out.println("用了"+(to-from)/1000+"秒");
        }catch (Exception e){
            e.printStackTrace();
            responseDto.setErrorInfo("search error!");
        }
        return responseDto;
    }

    //自定义分析
    @RequestMapping(value="/diyStatistic",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<DiyStatisticResponseDTO>> diyStatistic(@RequestBody List<DiyStatisticRequestDTO> diyStatisticRequestDTOList,
                                                      HttpServletRequest request) {

        ResponseDTO responseDto=new ResponseDTO();
        List<DiyStatisticResponseDTO> list = new ArrayList<>();
        try{
            surveyService.diyStatistic(diyStatisticRequestDTOList,list);
            responseDto.setResult(StatusConstant.SUCCESS);
            responseDto.setResponseData(list);
        }catch (Exception e){
            e.printStackTrace();
            responseDto.setErrorInfo("search error!");
        }
        return responseDto;
    }
}
