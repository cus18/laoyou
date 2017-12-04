package com.yhl.laoyou.modules.healthDataService.service.impl;

import com.yhl.laoyou.common.dto.practitioner.healthArchive.BasicInfoDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.modules.healthDataService.service.HomePageStatisticsService;
import com.yhl.laoyou.modules.sys.dao.SysElderUserDao;
import com.yhl.laoyou.modules.sys.dao.SysPractitionerUserDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by sunxiao on 2017/7/4.
 */
@Service
@Transactional(readOnly = false)
public class HomePageStatisticsServiceImpl implements HomePageStatisticsService{

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SysElderUserDao sysElderUserDao;

    @Autowired
    SysPractitionerUserDao sysPractitionerUserDao;

    @Override
    public JSONObject memberStatistics() throws Exception {
        JSONObject jsonObject = new JSONObject();
        try{
            int elderNum = sysElderUserDao.getSysElderUserCount();
            Query diabeticquery = new Query().addCriteria(Criteria.where("pastHistory").regex(".*?糖尿.*"));
            long diabeticNum = mongoTemplate.count(diabeticquery, BasicInfoDTO.class,"healthArchive_basicInfo");
            Query hypertensivequery = new Query().addCriteria(Criteria.where("pastHistory").regex(".*?高血.*"));
            long hypertensiveNum = mongoTemplate.count(hypertensivequery, BasicInfoDTO.class,"healthArchive_basicInfo");
            jsonObject.put("elderNum",elderNum);
            jsonObject.put("diabeticNum",diabeticNum);
            jsonObject.put("hypertensiveNum",hypertensiveNum);
        }catch (Exception e){
            throw new Exception("查询失败！");
        }
        return jsonObject;
    }

    @Override
    public JSONObject diabeticStatistics() {
        JSONObject jsonObject = new JSONObject();
        Query diabeticquery = new Query().addCriteria(Criteria.where("pastHistory").regex(".*?糖尿.*"));
        long diabeticNum = mongoTemplate.count(diabeticquery, BasicInfoDTO.class,"healthArchive_basicInfo");
        diabeticquery = new Query().addCriteria(Criteria.where("servicePackageTemplateName").regex(".*?糖尿.*"))
                .addCriteria(Criteria.where("status").is("ongoing"));
        long diabeticPackageNum = mongoTemplate.count(diabeticquery, HealthServicePackageDTO.class,"healthServicePackage");
        jsonObject.put("diabeticNum",diabeticNum);
        jsonObject.put("diabeticPackageNum",diabeticPackageNum);
        return jsonObject;
    }

    @Override
    public JSONObject hypertensiveStatistics() {
        JSONObject jsonObject = new JSONObject();
        Query hypertensivequery = new Query().addCriteria(Criteria.where("pastHistory").regex(".*?高血.*"));
        long hypertensiveNum = mongoTemplate.count(hypertensivequery, BasicInfoDTO.class,"healthArchive_basicInfo");
        hypertensivequery = new Query().addCriteria(Criteria.where("servicePackageTemplateName").regex(".*?高血.*"))
                .addCriteria(Criteria.where("status").is("ongoing"));
        long hypertensivePackageNum = mongoTemplate.count(hypertensivequery, HealthServicePackageDTO.class,"healthServicePackage");
        jsonObject.put("hypertensiveNum",hypertensiveNum);
        jsonObject.put("hypertensivePackageNum",hypertensivePackageNum);
        return jsonObject;
    }

    @Override
    public JSONObject doctorStatistics() {
        JSONObject jsonObject = new JSONObject();
        List<String> list = sysPractitionerUserDao.doctorAndNurseStatistics("医师");
        int count = 0;
        for(String temp : list){
            if(jsonObject.keySet().contains(temp)){
                jsonObject.put(temp,Integer.parseInt(String.valueOf(jsonObject.get(temp)))+1);

            }else{
                jsonObject.put(temp,1);
            }
            count++;
        }
        jsonObject.put("total",count);
        return jsonObject;
    }

    @Override
    public JSONObject nurseStatistics() {
        JSONObject jsonObject = new JSONObject();
        List<String> list = sysPractitionerUserDao.doctorAndNurseStatistics("护");
        int count = 0 ;
        for(String temp : list){
            if(jsonObject.keySet().contains(temp)){
                jsonObject.put(temp,Integer.parseInt(String.valueOf(jsonObject.get(temp)))+1);
            }else{
                jsonObject.put(temp,1);
            }
            count++;
        }
        jsonObject.put("total",count);
        return jsonObject;
    }
}
