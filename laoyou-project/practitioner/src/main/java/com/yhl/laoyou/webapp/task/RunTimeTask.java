package com.yhl.laoyou.webapp.task;


import com.yhl.laoyou.modules.healthService.service.MedicationPlanService;
import com.yhl.laoyou.modules.myService.service.ActivityService;
import com.yhl.laoyou.modules.sys.service.BannerService;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.HealthDataTaskService;
import com.yhl.laoyou.modules.weChatService.service.WechatCoreService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by zbm84 on 2017/5/8.
 */
public class RunTimeTask
{

    @Autowired
    private EasemobService easemobService;

    @Autowired
    private HealthDataTaskService healthDataService;

    @Autowired
    private MedicationPlanService medicationPlanService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    WechatCoreService wechatCoreService;

    @Autowired
    BannerService bannerService;

    public void updateEasemobToken() throws  Exception{
        easemobService.updateEasemobToken();

    }

    public void getNewReachData() throws  Exception{
        healthDataService.getHealthDataThread();
    }

    public void getEasemobChatRecord()throws  Exception{
        easemobService.getEasemobChatRecord();
    }


    public void medicationPlanLoad()throws  Exception{
        medicationPlanService.taskLoadMedicationPlan();
    }

    public void sendEasemobMessageByMedication()throws  Exception{
        medicationPlanService.sendEasemobMessageByMedication();
    }

    public void updateActivityStatus(){
        activityService.updateActivtyStatus();
    }

    public void sendActivityRemind(){
        activityService.sendReservationMessage();
    }

    public void updateWechatInfo(){
        wechatCoreService.updateWechatInfo();
    }

    public void timeUpdateBannerStatus(){
        bannerService.timeUpdateBannerStatus();
    }

}
