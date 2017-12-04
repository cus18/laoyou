package com.yhl.laoyou.modules.sys.service;

import com.google.common.collect.ImmutableList;
import com.yhl.laoyou.common.utils.ConstantUtil;
import com.yhl.laoyou.common.utils.HttpClientUtil;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.sys.dao.HealthDataDao;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;
import com.yhl.laoyou.modules.sys.entity.healthData.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取新锐时数据存入mongodb，定时任务用
 * Created by sunxiao on 2017/5/11.
 */
@Service
public class HealthDataTaskService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    HealthDataDao healthDataDao;

    @Autowired
    EasemobService easemobService;

    @Autowired
    UserService userService;

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    public void getHealthDataThread() {
        Runnable thread = new getHealthData();
        threadExecutor.execute(thread);
    }

    public class getHealthData extends Thread {

        @Override
        public void run() {
            getHealthData();
        }
    }

    public void getHealthData() {
        List<UserThirdPartRelVo> volist = healthDataDao.getThirdPartUserInfo();
        for(UserThirdPartRelVo relvo: volist){
            String url = ConstantUtil.NEWREACH_WEB_URL;
            String charset = "utf-8";
            long endTime = new Date().getTime();
            long startTime = endTime-24*60*60*1000;
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String httpOrgCreateTest = url + "nruaservice/user/login?devicetype=1&logintype=1";
            Map<String,String> createMap = new HashMap<String,String>();
            String httpOrgCreateTestRtn = httpClientUtil.doPostGetNzToken(httpOrgCreateTest,createMap,charset,relvo.getUserName()+":"+relvo.getPassword());
            System.out.println("result:" + httpOrgCreateTestRtn);

            ImmutableList types = ImmutableList.of("bp","po","bg","ecg","wt","pdr","bt");
            for(Object type : types){
                String queryUrl = url + "bizservice2/rest/health/databytime/"+type+"?startTime="+startTime+"&endTime="+endTime+"&page=0&pagesize=100";
                //String queryUrl = url + "bizservice2/rest/health/data/"+type+"?timetype=5&page=0&pagesize=100";
                String chronicData = httpClientUtil.doGetChronicData(queryUrl, createMap,charset, httpOrgCreateTestRtn);
                JSONObject myJsonObject = new JSONObject(chronicData);
                System.out.println(myJsonObject.get("data"));
                JSONArray data = (JSONArray) myJsonObject.get("data");
                if(data.length()>0) {
                    for (int i = 0; i < data.length(); i++) {
                        HealthData vo = new HealthData();
                        JSONObject healthData = data.getJSONObject(i);
                        vo.setMeasureId(UUID.randomUUID().toString());
                        vo.setElderId(relvo.getElderId());
                        setBaseAtr(healthData,vo);
                        vo.setType((String)type);
                        String message = "";
                        if("bp".equals(type)){
                            BloodPressureData info = new BloodPressureData();
                            setBaseAtr(healthData,info);
                            vo.setData(info);
                            updateData(vo);
                            message = easemobService.getEasemobMessageUrl("chatType4",vo.getMeasureId(),vo.getMeasureTime(),info.getDiastolic(),info.getSystolic(),info.getHeartRate());
                        }else if("po".equals(type)){
                            BloodOxygenData info = new BloodOxygenData();
                            setBaseAtr(healthData,info);
                            vo.setData(info);
                            updateData(vo);
                        }else if("bg".equals(type)){
                            BloodSugarData info = new BloodSugarData();
                            setBaseAtr(healthData,info);
                            Query query = null;
                            List<MeasurementRule> rule = null;
                            query = new Query().addCriteria(Criteria.where("elderId").is(vo.getElderId()))
                                    .addCriteria(Criteria.where("type").is(type));
                            rule = mongoTemplate.find(query, MeasurementRule.class);
                            if(rule==null || rule.size()==0){
                                query = new Query().addCriteria(Criteria.where("elderId").is("bloodSugarDefaultRole"))
                                        .addCriteria(Criteria.where("type").is(type));
                                rule = mongoTemplate.find(query, MeasurementRule.class);
                            }
                            if(rule!=null && rule.size()>0){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                BloodSugarRole role = ((BloodSugarRole)rule.get(0).getRule());
                                Field[] fields = BloodSugarRole.class.getDeclaredFields();
                                for(Field field:fields){
                                    String fieldName = field.getName();
                                    try {
                                        Method m = role.getClass().getMethod(
                                                "get" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()));
                                        String value = String.valueOf(m.invoke(role));
                                        if(value!=null && !"".equals(value)){
                                            updateData(vo,info, (String) type,value.split("-"),sdf,fieldName);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            vo.setData(info);
                            message = easemobService.getEasemobMessageUrl("chatType5",vo.getMeasureId(),info.getResult(),info.getMealType(),vo.getMeasureTime());
                        }else if("ecg".equals(type)){
                            ECGData info = new ECGData();
                            setBaseAtr(healthData,info);
                            vo.setData(info);
                            updateData(vo);
                        }else if("wt".equals(type)){
                            WeightData info = new WeightData();
                            setBaseAtr(healthData,info);
                            vo.setData(info);
                            updateData(vo);
                        }else {
                            continue;
                        }


                        Query query = new Query().addCriteria(Criteria.where("elderId").is(vo.getElderId()))
                                .addCriteria(Criteria.where("type").is(type))
                                .addCriteria(Criteria.where("measureTime").is(vo.getMeasureTime()));
                        List<HealthData> repeatData = mongoTemplate.find(query, HealthData.class);
                        if(repeatData==null || repeatData.size()==0){//防止重复数据
                            mongoTemplate.save(vo);
                            if(StringUtils.isNotNull(message)){
                                EasemobGroup easemobGroup = easemobService.getEasemobGroup(relvo.getElderId());
                                Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(),message);
                                threadExecutor.execute(thread);
                            }
                        }
                    }
                }
            }
        }
    }

    public void sendEasemobMessage(String easemobGroup,String message) {
        EasemobService.sendEasemobMessage(easemobGroup,message);
    }

    public class sendEasemobMessage extends Thread {

        private String easemobGroup;
        private String message;

        public sendEasemobMessage(String easemobGroup,String message) {
            this.easemobGroup = easemobGroup;
            this.message = message;
        }

        @Override
        public void run() {
            sendEasemobMessage(easemobGroup,message);
        }
    }

    private void updateData(HealthData vo){
        Query query = new Query().addCriteria(Criteria.where("elderId").is(vo.getElderId()))
                .addCriteria(Criteria.where("type").is(vo.getType()))
                .addCriteria(Criteria.where("measureTime").is(vo.getMeasureTime()));
        List<HealthData> repeatData = mongoTemplate.find(query, HealthData.class,"finalHealthData");
        if(repeatData==null || repeatData.size()==0){//防止重复数据
            mongoTemplate.save(vo,"finalHealthData");
        }
    }

    private void setBaseAtr(JSONObject healthData,Object info){
        try {
            List<Field> fieldList = new ArrayList<>() ;
            Class tempClass = info.getClass();
            while (tempClass !=null && !tempClass.getName().toLowerCase().equals("java.lang.object") )
            {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            for(Object keys : healthData.keySet()){
                for (Field field : fieldList) {
                    String keysStr = (String)keys;
                    if (field.getName().equals(keysStr)) {
                        field.setAccessible(true);
                        Object value = healthData.get(keysStr);
                        if(value.equals(null)){
                            value = "";
                        }else{
                            value = String.valueOf(value);
                        }
                        field.set(info, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateData(HealthData vo ,BloodSugarData info,String type,String[] time,SimpleDateFormat sdf,String period){
        try {
            String elderId = vo.getElderId();
            String measureTime = vo.getMeasureTime();
            if(sdf.parse(measureTime).getTime()>=sdf.parse(measureTime.split(" ")[0]+" "+time[0]).getTime() && sdf.parse(measureTime).getTime()<sdf.parse(measureTime.split(" ")[0]+" "+time[1]).getTime()){
                info.setPeriod(period);
                vo.setData(info);
                Query query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
                        .addCriteria(Criteria.where("type").is(type))
                        .addCriteria(Criteria.where("measureTime").gte(measureTime.split(" ")[0]+" "+time[0])
                                .andOperator(Criteria.where("measureTime").lte(measureTime.split(" ")[0]+" "+time[1])));
                List<HealthData> data = mongoTemplate.find(query, HealthData.class,"finalHealthData");
                if(data!=null&&data.size()>0){
                    for(HealthData temp:data){
                        if(sdf.parse(temp.getMeasureTime()).getTime()<sdf.parse(measureTime).getTime()){
                            mongoTemplate.remove(query,HealthData.class,"finalHealthData");
                            mongoTemplate.save(vo,"finalHealthData");
                        }
                    }
                }else{
                    mongoTemplate.save(vo,"finalHealthData");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
