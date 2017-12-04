package com.yhl.laoyou.modules.healthService.service.impl;

import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanTimingDTO;
import com.yhl.laoyou.common.utils.ConstantUtil;
import com.yhl.laoyou.modules.healthService.service.MedicationPlanService;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.UserService;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zbm84 on 2017/5/25.
 */
@Service
@Transactional(readOnly = false)
public class MedicationPlanServiceImpl implements MedicationPlanService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @Override
    public List<MedicationPlanDTO> getAllMedicationPlanByUser(String elderID) {
        Query query = new Query().addCriteria(Criteria.where("elderID").is(elderID));
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return mongoTemplate.find(query,MedicationPlanDTO.class, "MedicationPlan");
    }

    @Override
    public Integer insertMedicationPlan(MedicationPlanDTO medicationPlanDTO) {
        try {
            medicationPlanDTO.setId(UUIDUtil.getUUID());
            medicationPlanDTO.setCompleteStatus("");
            mongoTemplate.insert(medicationPlanDTO, "MedicationPlan");
            SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            String dates=sdfs.format(date);
            if(dates.equals(medicationPlanDTO.getStartTime())){
                String[] usageTime=medicationPlanDTO.getUsageTime().split(";");
                for (int i = 0; i < usageTime.length; i++) {
                    String[] time=usageTime[i].split(":");
                    Integer hour=Integer.parseInt(time[0]);
                    Integer min=Integer.parseInt(time[1]);
                    if((date.getHours()==hour&&min>date.getMinutes())||(date.getHours()>hour)) {
                        MedicationPlanTimingDTO medicationPlanTimingDTO = new MedicationPlanTimingDTO();
                        medicationPlanTimingDTO.setId(UUIDUtil.getUUID());
                        medicationPlanTimingDTO.setMedicationPlanID(medicationPlanDTO.getId());
                        medicationPlanTimingDTO.setStatus("");
                        medicationPlanTimingDTO.setUsageTime(dates + " " + usageTime[i]);
                        medicationPlanTimingDTO.setCreateDate(new Date());
                        medicationPlanTimingDTO.setDrugName(medicationPlanDTO.getDrugName());
                        medicationPlanTimingDTO.setDose(medicationPlanDTO.getDose());
                        this.mongoTemplate.insert(medicationPlanTimingDTO, "MedicationPlan_Timing");
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer updateMedicationPlan(MedicationPlanDTO medicationPlanDTO) {
        try {
            Query query = new Query().addCriteria(Criteria.where("_id").is(medicationPlanDTO.getId()));
            mongoTemplate.findAndRemove(query,MedicationPlanDTO.class,"MedicationPlan");
            medicationPlanDTO.setCompleteStatus("");
            mongoTemplate.insert(medicationPlanDTO, "MedicationPlan");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer deleteMedicationPlan(MedicationPlanDTO medicationPlanDTO) {
        try {
            Query query = new Query().addCriteria(Criteria.where("_id").is(medicationPlanDTO.getId()));
            mongoTemplate.findAndRemove(query,MedicationPlanDTO.class,"MedicationPlan");
            Query querys = new Query().addCriteria(Criteria.where("medicationPlanID").is(medicationPlanDTO.getId()));
            mongoTemplate.findAllAndRemove(querys, MedicationPlanTimingDTO.class,"MedicationPlan_Timing");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public MedicationPlanDTO getMedicationPlanByID(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return mongoTemplate.findOne(query, MedicationPlanDTO.class, "MedicationPlan");
    }

    @Override
    public List<MedicationPlanDTO> getCompleteMedicationPlanByUser(String elderID) {
        Query query = new Query().addCriteria(Criteria.where("completeStatus").ne("")).addCriteria(Criteria.where("elderID").is(elderID));
        query.with(new Sort(Sort.Direction.DESC,"startTime"));
        return mongoTemplate.find(query, MedicationPlanDTO.class, "MedicationPlan");
    }

    @Override
    public List<MedicationPlanDTO> getUncompletedMedicationPlanByUser(String elderID) throws  Exception{
        SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd");
        Criteria criteria = new Criteria();
        Query query = new Query()
                .addCriteria(Criteria.where("endTime").gt(sdfs.format(new Date())))
                .addCriteria(criteria)
                .addCriteria(Criteria.where("completeStatus").is(""))
                .addCriteria(Criteria.where("elderID").is(elderID));
        query.with(new Sort(Sort.Direction.DESC,"startTime"));
        List<MedicationPlanDTO> list=mongoTemplate.find(query, MedicationPlanDTO.class, "MedicationPlan");
        for (MedicationPlanDTO medicationPlan:list) {
            SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date time=new Date();
            Calendar cal = Calendar.getInstance();
            Date startTime=sdfs.parse(medicationPlan.getStartTime());
            if(time.getTime()<startTime.getTime()){
                cal.setTime(startTime);
            }
            Integer weekDay = cal.get(Calendar.DAY_OF_WEEK)-1;
            String[] repeatArray=medicationPlan.getRepeat().split(";");
            Integer dayDiff=-1;
            for (int i=0;i<repeatArray.length;i++) {
                String repeat=repeatArray[i];
                if(repeat.equals("7")){
                    break;
                }else{
                    Integer re=Integer.parseInt(repeat);
                    if(re>weekDay){
                        Integer diff=re-weekDay;
                        if((dayDiff!=0&&diff<dayDiff)||dayDiff==-1){
                            dayDiff=diff;
                        }
                    }else if(re<weekDay){
                        Integer diff=(re+6)-weekDay;
                        if((dayDiff!=0&&diff<dayDiff)||dayDiff==-1){
                            dayDiff=diff;
                        }
                    }else{
                        dayDiff=0;
                    }
                }
            }
            cal.add(Calendar.DAY_OF_MONTH,dayDiff);
            String[] usageTimeArray=medicationPlan.getUsageTime().split(";");
            Integer diff=0;
            String timeLine="";
            Integer timeDif=0;
            Integer minimal=0;
            Integer minimalArray=0;
            for (int i=0;i<usageTimeArray.length;i++){
                Date  times=sdff.parse(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE)+" "+usageTimeArray[i]);
                Long tDiff=times.getTime()-time.getTime();
                if(tDiff==0||(diff==0||diff<0)||(diff>0&&tDiff>0&&tDiff<diff)){
                    diff=tDiff.intValue()==0?1:tDiff.intValue();
                    timeLine=usageTimeArray[i];
                    timeDif=(tDiff.intValue()/1000/60);
                }
                if(tDiff<minimal){
                    minimal=tDiff.intValue();
                    minimalArray=i;
                }
            }
            if(diff<0){
                timeLine=usageTimeArray[minimalArray];
                Date times=sdff.parse(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE)+" "+timeLine);
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(times);
                rightNow.add(Calendar.DAY_OF_YEAR, 1);
                times=rightNow.getTime();
                Long d=times.getTime()-time.getTime();
                timeDif=(d.intValue()/1000/60);
            }
            medicationPlan.setTimeLine(timeLine);
            Integer hourDif=timeDif/60;
            Integer dayDif=timeDif/60/24;
            if(dayDif>0){
                 hourDif=hourDif-dayDif*24;
                Integer minuteDif=timeDif%60;
                if(minuteDif>0){
                    medicationPlan.setMinuteLine(dayDif.toString() + "天"+hourDif.toString()+"小时"+minuteDif+"分钟");
                }else if(hourDif>0){
                    medicationPlan.setMinuteLine(dayDif.toString() + "天"+hourDif.toString()+"小时");
                } else {
                    medicationPlan.setMinuteLine(dayDif.toString() + "天");
                }
            }else if(hourDif>0) {
                Integer minuteDif=timeDif%60;
                if(minuteDif>0){
                    medicationPlan.setMinuteLine(hourDif.toString()+"小时"+minuteDif+"分钟");
                }else {
                    medicationPlan.setMinuteLine(hourDif.toString() + "小时");
                }
            }else{
                medicationPlan.setMinuteLine(timeDif.toString()+"分钟");
            }

        }
        return list;
    }


    /**
     * 每天 23：51 扫描第二天需要执行的任务
     */
    @Override
    public void taskLoadMedicationPlan() throws  Exception{
        SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdfs.parse(sdfs.format(new Date()));
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR, 1);
        dt = rightNow.getTime();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("repeat").is("7;"),Criteria.where("repeat").regex(".*?"+dt.getDay()+".*"));
        Query query = new Query()
                .addCriteria(Criteria.where("endTime").gt(sdfs.format(dt)).and("startTime").lte(sdfs.format(dt)))
                .addCriteria(criteria)
                .addCriteria(Criteria.where("completeStatus").is(""));
        List<MedicationPlanDTO> list=mongoTemplate.find(query, MedicationPlanDTO.class, "MedicationPlan");
        for (MedicationPlanDTO medicationPlan : list) {
            String[] time = medicationPlan.getUsageTime().split(";");
            for (String t : time) {
                MedicationPlanTimingDTO medicationPlanTimingDTO = new MedicationPlanTimingDTO();
                medicationPlanTimingDTO.setId(UUIDUtil.getUUID());
                medicationPlanTimingDTO.setMedicationPlanID(medicationPlan.getId());
                medicationPlanTimingDTO.setStatus("");
                medicationPlanTimingDTO.setUsageTime(sdfs.format(dt)+" "+t);
                medicationPlanTimingDTO.setCreateDate(dt);
                medicationPlanTimingDTO.setDrugName(medicationPlan.getDrugName());
                medicationPlanTimingDTO.setDose(medicationPlan.getDose());
                this.mongoTemplate.insert(medicationPlanTimingDTO, "MedicationPlan_Timing");
            }
        }
        //修改已完成的任务状态
//        Query query1 = new Query().addCriteria(Criteria.where("endTime").is(dt));
//        WriteResult writeResult=this.mongoTemplate.updateMulti(query,new Update().addToSet("completeStatus",new Date()),MedicationPlanDTO.class);
    }

    @Override
    public MedicationPlanDTO getMedicationPlanByUser(String medicationPlanID) {
        Query query=new Query().addCriteria(Criteria.where("_id").is(medicationPlanID));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createDate")));
        return mongoTemplate.findOne(query,MedicationPlanDTO.class,"MedicationPlan");
    }

    @Override
    public Integer updateMedicationPlanStatus(String medicationPlanID, String status) {
        try {
            Query query=new Query().addCriteria(Criteria.where("_id").is(medicationPlanID));
            Update update=new Update();
            update.set("status",status);
            mongoTemplate.updateFirst(query,update,"MedicationPlan_Timing");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 定时器提前十分钟扫描需要执行的任务，
     */
    @Override
    public void sendEasemobMessageByMedication()throws  Exception {
        SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dt = sdfs.parse(sdfs.format(new Date()));
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MINUTE, 3);
        dt = rightNow.getTime();
        String reStr = sdfs.format(dt);
        Query query = new Query().addCriteria(Criteria.where("status").is("").and("usageTime").is(reStr));
        List<MedicationPlanTimingDTO> list = this.mongoTemplate.find(query, MedicationPlanTimingDTO.class, "MedicationPlan_Timing");
        for (MedicationPlanTimingDTO medicationPlanTimingDTO : list) {
            Query querys=new Query().addCriteria(Criteria.where("_id").is(medicationPlanTimingDTO.getMedicationPlanID()));
            MedicationPlanDTO medicationPlanDTO = mongoTemplate.findOne(querys,MedicationPlanDTO.class,"MedicationPlan");
            String elderID=userService.getEasemobGroupIDByElderID(medicationPlanDTO.getElderID());
            EasemobService.sendEasemobMessage(elderID,"practitionerURL@@@"+ ConstantUtil.EASEMOB_MESSAGE_URL+"/practitioner#/groupChat/chatType8,"+medicationPlanTimingDTO.getId()+"@@@"+ ConstantUtil.EASEMOB_MESSAGE_URL+"/practitioner#/medicationRemind/"+medicationPlanTimingDTO.getId());
        }
    }

    @Override
    public MedicationPlanTimingDTO getMedicationPlanTimingByID(String id){
        Query query=new Query().addCriteria(Criteria.where("_id").is(id));
        query.with(new Sort(Sort.Direction.DESC, "createDate"));
        MedicationPlanTimingDTO medicationPlanTimingDTO = mongoTemplate.findOne(query,MedicationPlanTimingDTO.class,"MedicationPlan_Timing");
        return medicationPlanTimingDTO;
    }

    @Override
    public List<MedicationPlanTimingDTO> getMedicationPlanTimingByElderUserID(String elderUserId,String startTime,String endTime) {
        Query query = new Query().addCriteria(Criteria.where("elderID").is(elderUserId));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
        List<MedicationPlanDTO> list= mongoTemplate.find(query,MedicationPlanDTO.class, "MedicationPlan");
        List<MedicationPlanTimingDTO> resultList=new ArrayList<>();
        for (MedicationPlanDTO m:list) {
            Query querys = new Query().addCriteria(Criteria.where("medicationPlanID").is(m.getId()))
                    .addCriteria(Criteria.where("usageTime").gte(startTime).andOperator(Criteria.where("usageTime").lte(endTime+" 23:59")));
            query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "usageTime")));
            List<MedicationPlanTimingDTO> lists= mongoTemplate.find(querys,MedicationPlanTimingDTO.class, "MedicationPlan_Timing");
            for (MedicationPlanTimingDTO mt:lists) {
                mt.setUsageDate(mt.getUsageTime().split(" ")[0]);
                mt.setUsageTime(mt.getUsageTime().split(" ")[1]);
                resultList.add(mt);
            }
        }
        return resultList;
    }


}
