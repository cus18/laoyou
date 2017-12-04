package com.yhl.laoyou.common.dto.practitioner.healthService;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zbm84 on 2017/6/1.
 */
public class MedicationAllPlanDTO {

    @JSONField(name = "complete")
    private List<MedicationPlanDTO> complete;

    @JSONField(name = "uncompleted")
    private List<MedicationPlanDTO> uncompleted;


    public List<MedicationPlanDTO> getComplete() {
        return complete;
    }

    public void setComplete(List<MedicationPlanDTO> complete) {
        this.complete = complete;
    }

    public List<MedicationPlanDTO> getUncompleted() {
        return uncompleted;
    }

    public void setUncompleted(List<MedicationPlanDTO> uncompleted) {
        this.uncompleted = uncompleted;
    }
}
