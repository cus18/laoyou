package com.yhl.laoyou.modules.healthService.service;

import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.MedicationPlanTimingDTO;

import java.util.List;

/**
 * Created by zbm84 on 2017/5/25.
 */
public interface MedicationPlanService {

    List<MedicationPlanDTO> getAllMedicationPlanByUser(String practitionUserID);

    Integer insertMedicationPlan(MedicationPlanDTO medicationPlanDTO);

    Integer updateMedicationPlan(MedicationPlanDTO medicationPlanDTO);

    Integer deleteMedicationPlan(MedicationPlanDTO medicationPlanDTO);

    List<MedicationPlanDTO> getCompleteMedicationPlanByUser(String practitionUserID) ;

    List<MedicationPlanDTO> getUncompletedMedicationPlanByUser(String practitionUserID) throws  Exception;

    void sendEasemobMessageByMedication()throws  Exception;

    void taskLoadMedicationPlan() throws  Exception;

    MedicationPlanDTO getMedicationPlanByUser(String medicationPlanID) ;

    Integer updateMedicationPlanStatus(String medicationPlanID,String status);

    MedicationPlanDTO getMedicationPlanByID(String id);

    MedicationPlanTimingDTO getMedicationPlanTimingByID(String id);

    List<MedicationPlanTimingDTO> getMedicationPlanTimingByElderUserID(String id,String startTime,String endTime);
}
