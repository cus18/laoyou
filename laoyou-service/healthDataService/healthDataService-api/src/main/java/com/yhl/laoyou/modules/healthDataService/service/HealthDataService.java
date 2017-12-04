package com.yhl.laoyou.modules.healthDataService.service;

import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.detection.DetectionDTO;
import com.yhl.laoyou.common.dto.practitioner.test.TestReportDTO;
import com.yhl.laoyou.common.dto.practitioner.treatment.TreatmentDTO;
import com.yhl.laoyou.modules.sys.entity.healthData.HealthData;

import java.util.List;

/**
 * Created by sunxiao on 2017/5/11.
 */
public interface HealthDataService {

    ResponseDTO<List> getTestReport(String elderId, String startDate, String endDate);

    ResponseDTO<List> getTreatmentRecord(String elderId, String startDate, String endDate);

    ResponseDTO<DetectionDTO> getHealthDataFromMongo(String detectionType, String detectionDateType, String elderId);

    HealthData getSingleHealthDataFromMongo(String id);

    TestReportDTO getSingleTestReportFromMongo(String id);

    void saveHealthDataToMongo(DetectionDTO detectionDTO) throws Exception;

    void saveControlTargetToMongo(DetectionDTO detectionDTO);

    DetectionDTO getControlTargetFromMongo(String elderId, String detectionType);

    void createTestReport(TestReportDTO testReportDTO) throws Exception;

    void createTreatment(TreatmentDTO treatmentDTO) throws Exception;

    TreatmentDTO getSingleTreatmentRecordFromMongo(String id);

    void makeHealthData();
}
