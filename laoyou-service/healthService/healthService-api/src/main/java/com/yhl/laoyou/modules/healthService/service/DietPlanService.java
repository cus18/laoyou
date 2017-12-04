package com.yhl.laoyou.modules.healthService.service;


import com.yhl.laoyou.common.dto.practitioner.healthService.DietPlanDTO;

import java.util.List;

/**
 * Created by zbm84 on 2017/5/27.
 */
public interface DietPlanService {

    List<DietPlanDTO> getAllDietPlan(String elderID);

    Integer insertDietPlan(DietPlanDTO dietPlanDTO);

    DietPlanDTO getDietPlan(String dietPlanID);

    List<DietPlanDTO> getAllDietPlanByDate(DietPlanDTO dietPlanDTO);

}
