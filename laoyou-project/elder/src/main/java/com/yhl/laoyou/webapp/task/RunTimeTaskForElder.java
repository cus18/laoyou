package com.yhl.laoyou.webapp.task;

import com.yhl.laoyou.modules.healthDataService.service.HealthDataService;
import com.yhl.laoyou.modules.myService.service.SurveyService;
import com.yhl.laoyou.modules.weChatService.service.WechatCoreService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sunxiao on 2017/9/14.
 */
public class RunTimeTaskForElder {

    @Autowired
    WechatCoreService wechatCoreService;

    @Autowired
    SurveyService surveyService;

    @Autowired
    HealthDataService healthDataService;

    public void updateWechatInfo(){
        wechatCoreService.updateWechatInfo();
    }

    public void arrangementSurveyInfo(){
        surveyService.arrangementSurveyInfo();
    }

    public void makeHealthData(){
        healthDataService.makeHealthData();
    }
}
