package com.yhl.laoyou.modules.healthDataService.service.impl;

import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.detection.DetectionDTO;
import com.yhl.laoyou.common.dto.practitioner.test.TestReportDTO;
import com.yhl.laoyou.common.dto.practitioner.treatment.TreatmentDTO;
import com.yhl.laoyou.common.utils.DateUtils;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.healthDataService.entity.TestReport;
import com.yhl.laoyou.modules.healthDataService.entity.TreatmentRecord;
import com.yhl.laoyou.modules.healthDataService.service.HealthDataService;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;
import com.yhl.laoyou.modules.sys.entity.healthData.*;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunxiao on 2017/5/11.
 */
@Service
@Transactional(readOnly = false)
public class HealthDataServiceImpl implements HealthDataService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    EasemobService easemobService;

    @Autowired
    UserService userService;

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

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

    @Override
    public ResponseDTO<DetectionDTO> getHealthDataFromMongo(String detectionType, String detectionDateType, String elderId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateNow = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dateNow);
        if(detectionDateType.equals("day"))
        {
            cl.add(Calendar.DAY_OF_YEAR, -1);
        }else if(detectionDateType.equals("week"))
        {
            cl.add(Calendar.WEEK_OF_YEAR, -1);
        }else if(detectionDateType.equals("month")){
            cl.add(Calendar.MONTH, -1);
        }
        else if(detectionDateType.equals("threeMonth")){
            cl.add(Calendar.MONTH, -3);
        }
        else if(detectionDateType.equals("halfYear")){
            cl.add(Calendar.MONTH, -6);
        }
        Date dateFrom = cl.getTime();
        Query query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
                .addCriteria(Criteria.where("type").is(detectionType))
                .addCriteria(Criteria.where("measureTime").gte(sdf.format(dateFrom))
                        .andOperator(Criteria.where("measureTime").lte(sdf.format(dateNow))));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "measureTime")));
        List<HealthData> data = mongoTemplate.find(query, HealthData.class,"finalHealthData");

        ResponseDTO<DetectionDTO> responseDTO = new ResponseDTO<>();
        DetectionDTO detectionDTO = new DetectionDTO();
        detectionDTO.setElderId(elderId);
        detectionDTO.setDetectionType(detectionDateType);
        List detectionDataList = new ArrayList<>();
        for(HealthData healthData : data)
        {
            detectionDataList.add(healthData.getData());
        }
        detectionDTO.setDetectionData(detectionDataList);
        responseDTO.setResponseData(detectionDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List> getTestReport(String elderId , String startDate, String endDate) {
        ResponseDTO<List> responseDTO = new ResponseDTO<List>();
        Query query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
                .addCriteria(Criteria.where("testDate").gte(startDate)
                        .andOperator(Criteria.where("testDate").lte(endDate)));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "testDate"))).with(new Sort(new Sort.Order(Sort.Direction.DESC, "testTime")));
        List<TestReport> data = mongoTemplate.find(query, TestReport.class, "testReport");
        responseDTO.setResponseData(data);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List> getTreatmentRecord(String elderId , String startDate, String endDate) {
        ResponseDTO<List> responseDTO = new ResponseDTO<List>();
        Query query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
                .addCriteria(Criteria.where("recordDate").gte(startDate+" 00:00")
                        .andOperator(Criteria.where("recordDate").lte(endDate+" 23:59")));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "recordDate")));
        List<TreatmentRecord> data = mongoTemplate.find(query, TreatmentRecord.class, "treatmentRecord");
        responseDTO.setResponseData(data);
        return responseDTO;
    }

    @Override
    public void saveHealthDataToMongo(DetectionDTO detectionDTO) throws Exception {
        HealthData vo = new HealthData();
        vo.setElderId(detectionDTO.getElderId());
        vo.setPname(detectionDTO.getElderName());
        vo.setType(detectionDTO.getDetectionType());
        String measureTime = ((CommonData)detectionDTO.getDetectionData().get(0)).getMeasureTime();
        if(DateUtils.StrToDate(measureTime,"yyyy-MM-dd HH:mm").getTime() > new Date().getTime()){
            throw new Exception("测量时间不能晚于当前时间！");
        }
        vo.setMeasureTime(measureTime);
        vo.setMeasureId(UUID.randomUUID().toString());
        String message = "";
        if("bg".equals(vo.getType())){
            BloodSugarData info = new BloodSugarData();
            info.setResult(((CommonData)detectionDTO.getDetectionData().get(0)).getBgValue());
            info.setMealType(((CommonData)detectionDTO.getDetectionData().get(0)).getMealType());
            info.setMeasureTime(measureTime);
            info.setRemark(((CommonData)detectionDTO.getDetectionData().get(0)).getRemarks());
            updateData(vo,info,vo.getType());
            vo.setData(info);
            //'/bloodSugarRecord/:bloodSugarNum,:recorded,:timeType,:timeDate,:readOnly
            message = easemobService.getEasemobMessageUrl("chatType5",vo.getMeasureId(),info.getResult(),info.getMealType(),vo.getMeasureTime());
        }else if("bp".equals(vo.getType())){
            BloodPressureData info = new BloodPressureData();
            info.setSystolic(((CommonData)detectionDTO.getDetectionData().get(0)).getSystolic());
            info.setDiastolic(((CommonData)detectionDTO.getDetectionData().get(0)).getDiastolic());
            info.setHeartRate(((CommonData)detectionDTO.getDetectionData().get(0)).getHeartRate());
            info.setMeasureTime(measureTime);
            info.setRemark(((CommonData)detectionDTO.getDetectionData().get(0)).getRemarks());
            vo.setData(info);
            mongoTemplate.save(vo,"finalHealthData");
            message = easemobService.getEasemobMessageUrl("chatType4",vo.getMeasureId(),vo.getMeasureTime(),info.getDiastolic(),info.getSystolic(),info.getHeartRate());
        }else{
            throw new Exception("类型不存在！");
        }
        mongoTemplate.save(vo);

        if(StringUtils.isNotNull(message)){
            EasemobGroup easemobGroup = easemobService.getEasemobGroup(detectionDTO.getElderId());
            Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(),message);
            threadExecutor.execute(thread);
        }
    }

    @Override
    public void saveControlTargetToMongo(DetectionDTO detectionDTO) {
        ControlTargetData ct = (ControlTargetData)detectionDTO.getDetectionData().get(0);
        ct.setElderId(detectionDTO.getElderId());
        ct.setElderName(detectionDTO.getElderName());
        ct.setType(detectionDTO.getDetectionType());
        Query query = new Query().addCriteria(Criteria.where("elderId").is(ct.getElderId()))
                .addCriteria(Criteria.where("type").is(ct.getType()));
        mongoTemplate.remove(query,ct.getClass());
        mongoTemplate.save(ct);
    }

    @Override
    public DetectionDTO getControlTargetFromMongo(String elderId, String detectionType) {
        Query query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
                .addCriteria(Criteria.where("type").is(detectionType));
        List list = mongoTemplate.find(query,ControlTargetData.class);
        DetectionDTO detectionDTO = new DetectionDTO();
        detectionDTO.setDetectionData(list);
        return detectionDTO;
    }

    @Override
    public void createTestReport(TestReportDTO testReportDTO) throws Exception {
        TestReport testReport = new TestReport();
        testReport.setReportId(UUID.randomUUID().toString());
        testReport.setElderId(testReportDTO.getElderId());
        testReport.setElderName(testReportDTO.getElderName());
        testReport.setProviderId(testReportDTO.getProviderId());
        testReport.setProviderName(testReportDTO.getProviderName());
        testReport.setTestDate(DateUtils.getDate("yyyy-MM-dd"));
        testReport.setTestTime(DateUtils.getDate("HH:mm:ss"));
        testReport.setTestPicUrl(testReportDTO.getReportImage());
        testReport.setDescription(testReportDTO.getDescription());
        try{
            mongoTemplate.save(testReport);
        }catch (Exception e){
            throw new Exception("createTestReport failure");
        }

        String message = easemobService.getEasemobMessageUrl("chatType6",testReport.getReportId(),testReport.getTestDate(),testReport.getTestTime());

        EasemobGroup easemobGroup = easemobService.getEasemobGroup(testReportDTO.getElderId());
        Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(),message);
        threadExecutor.execute(thread);
    }

    @Override
    public void createTreatment(TreatmentDTO treatmentDTO) throws Exception {
        TreatmentRecord treatmentRecord = new TreatmentRecord();
        treatmentRecord.setTreatmentId(UUID.randomUUID().toString());
        treatmentRecord.setElderId(treatmentDTO.getElderId());
        treatmentRecord.setElderName(treatmentDTO.getElderName());
        treatmentRecord.setProviderId(treatmentDTO.getProviderId());
        treatmentRecord.setProviderName(treatmentDTO.getProviderName());
        treatmentRecord.setRecordDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        treatmentRecord.setAudioUrl(treatmentDTO.getTreatmentAudio());
        treatmentRecord.setDescription(treatmentDTO.getDescription());
        try{
            mongoTemplate.save(treatmentRecord);
        }catch (Exception e){
            throw new Exception("createTestReport failure");
        }



        String message = easemobService.getEasemobMessageUrl("chatType7",treatmentRecord.getTreatmentId(),treatmentRecord.getRecordDate());
        EasemobGroup easemobGroup = easemobService.getEasemobGroup(treatmentDTO.getElderId());
        Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(),message);
        threadExecutor.execute(thread);
    }

    private void updateData(HealthData vo , BloodSugarData info, String type){
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
                        String[] time = value.split("-");
                        String elderId = vo.getElderId();
                        String measureTime = vo.getMeasureTime();
                        if(sdf.parse(measureTime).getTime()>=sdf.parse(measureTime.split(" ")[0]+" "+time[0]).getTime() && sdf.parse(measureTime).getTime()<sdf.parse(measureTime.split(" ")[0]+" "+time[1]).getTime()){
                            info.setPeriod(fieldName);
                            vo.setData(info);
                            query = new Query().addCriteria(Criteria.where("elderId").is(elderId))
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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public TestReportDTO getSingleTestReportFromMongo(String id) {
        Query query = new Query().addCriteria(Criteria.where("reportId").is(id));
        List<TestReport> data = mongoTemplate.find(query, TestReport.class, "testReport");
        TestReportDTO testReportDTO = new TestReportDTO();
        testReportDTO.setElderId(data.get(0).getElderId());
        testReportDTO.setDescription(data.get(0).getDescription());
        testReportDTO.setElderName(data.get(0).getElderName());
        testReportDTO.setProviderId(data.get(0).getProviderId());
        testReportDTO.setProviderName(data.get(0).getProviderName());
        testReportDTO.setReportId(data.get(0).getReportId());
        testReportDTO.setReportImage(data.get(0).getTestPicUrl());
        testReportDTO.setTestReportDate(data.get(0).getTestDate());
        testReportDTO.setUpdateDate(data.get(0).getTestDate());
        return testReportDTO;
    }

    @Override
    public TreatmentDTO getSingleTreatmentRecordFromMongo(String id) {
        Query query = new Query().addCriteria(Criteria.where("treatmentId").is(id));
        List<TreatmentRecord> data = mongoTemplate.find(query, TreatmentRecord.class, "treatmentRecord");
        TreatmentDTO treatmentDTO = new TreatmentDTO();
        treatmentDTO.setUpdateDate(data.get(0).getRecordDate());
        treatmentDTO.setProviderName(data.get(0).getProviderName());
        treatmentDTO.setProviderId(data.get(0).getProviderId());
        treatmentDTO.setElderName(data.get(0).getElderName());
        treatmentDTO.setElderId(data.get(0).getElderId());
        treatmentDTO.setTreatmentAudio(data.get(0).getAudioUrl());
        treatmentDTO.setTreatmentData(data.get(0).getRecordDate());
        treatmentDTO.setTreatmentId(data.get(0).getTreatmentId());
        return treatmentDTO;
    }

    @Override
    public HealthData getSingleHealthDataFromMongo(String id) {
        Query query = new Query().addCriteria(Criteria.where("measureId").is(id));
        List<HealthData> data = mongoTemplate.find(query, HealthData.class,"finalHealthData");
        return data.get(0);
    }

    @Override
    public void makeHealthData() {
        String name = "李丛蓉";
        String elderId = "2f06357d6c78463caea8df38b142f3d0";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
        makebpData(name,elderId,yesterday);
        makeBgData(name,elderId,yesterday);
        makeSportData(name,elderId,yesterday);
    }

    private void makebpData(String name,String elderId,String yesterday){
        try {
            HealthData temp = new HealthData();
            int j=0;
            Random r = new Random();
            String[] tt = new String[]{"05:","06:","08:","11:","13:","16:","19:","21:"};
            Map map = new HashMap();
            map.put("05:","dawn");
            map.put("06:","beforeBreakFast");
            map.put("08:","afterBreakFast");
            map.put("11:","beforeLunch");
            map.put("13:","afterLunch");
            map.put("16:","beforeDinner");
            map.put("19:","afterDinner");
            map.put("21:","beforeSleep");
            for(String t: tt){
                j++;
                int mm = (10+r.nextInt(50));
                float mmm = r.nextInt(10);
                BloodPressureData d = new BloodPressureData();
                d.setMeasureTime(yesterday+" "+t+mm);
                d.setRecvTime(yesterday+" "+t+mm);
                d.setId(1000+j+"");
                d.setResult(mmm/10+5+"");
                d.setHeartRate((40+r.nextInt(120))+"");
                d.setDiastolic(r.nextInt(200)+"");
                d.setSystolic(r.nextInt(200)+"");
                d.setPname(name);
                temp.setMeasureTime(yesterday+" "+t+mm);
                temp.setRecvTime(yesterday+" "+t+mm);
                temp.setData(d);
                temp.setType("bp");
                temp.setElderId(elderId);
                temp.setMeasureId(UUID.randomUUID().toString());
                mongoTemplate.save(temp,"finalHealthData");
                System.out.println("=====================save==================="+yesterday+" "+t+mm);
            }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeBgData(String name,String elderId,String yesterday){
        try {
            int j=0;
            Random r = new Random();
            String[] tt = new String[]{"05:","06:","08:","11:","13:","16:","19:","21:"};
            Map map = new HashMap();
            map.put("05:","dawn");
            map.put("06:","beforeBreakFast");
            map.put("08:","afterBreakFast");
            map.put("11:","beforeLunch");
            map.put("13:","afterLunch");
            map.put("16:","beforeDinner");
            map.put("19:","afterDinner");
            map.put("21:","beforeSleep");
            for(String t: tt){
                HealthData temp = new HealthData();
                j++;
                int mm = (10+r.nextInt(50));
                float mmm = r.nextInt(10);
                BloodSugarData d = new BloodSugarData();
                d.setMeasureTime(yesterday+" "+t+mm);
                d.setRecvTime(yesterday+" "+t+mm);
                d.setId(1000+j+"");
                d.setResult(mmm/10+5+"");
                d.setPeriod((String) map.get(t));
                d.setPname(name);
                temp.setMeasureTime(yesterday+" "+t+mm);
                temp.setRecvTime(yesterday+" "+t+mm);
                temp.setData(d);
                temp.setPname(name);
                temp.setType("bg");
                temp.setElderId(elderId);
                temp.setMeasureId(UUID.randomUUID().toString());
                mongoTemplate.save(temp,"finalHealthData");
                System.out.println("====================save=================="+yesterday+" "+t+mm);
            }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeSportData(String name,String elderId,String yesterday){
        try {
            String[] tt = new String[]{"11:", "13:", "16:", "19:"};
            Random r = new Random();
            HealthData healthData = new HealthData();
            SportData sd = new SportData();
            sd.setMeasureTime(yesterday+" "+tt[r.nextInt(4)]+(10+r.nextInt(50))+":"+(10+r.nextInt(50)));
            sd.setMdevice("00000001");
            sd.setStepCount((1000+r.nextInt(3000))+"");
            sd.setStepLength((60+r.nextInt(10))+"");
            sd.setConsumeHeat((70+r.nextInt(10))+"");
            sd.setBurnFat((60+r.nextInt(15))+"");
            sd.setWeight((60+r.nextInt(10))+"");
            healthData.setMeasureTime(yesterday+" "+tt[r.nextInt(4)]+(10+r.nextInt(50))+":"+(10+r.nextInt(50)));
            healthData.setElderId(elderId);
            healthData.setPname(name);
            healthData.setRecvTime(yesterday+" "+tt[r.nextInt(4)]+(10+r.nextInt(50))+":"+(10+r.nextInt(50)));
            healthData.setType("pdr");
            healthData.setData(sd);
            System.out.println("=======================save===================="+yesterday);
            mongoTemplate.save(healthData,"finalHealthData");
        }catch (Exception e){

        }
    }
}
