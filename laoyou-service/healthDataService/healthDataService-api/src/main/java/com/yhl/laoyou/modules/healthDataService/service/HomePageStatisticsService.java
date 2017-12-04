package com.yhl.laoyou.modules.healthDataService.service;

import net.sf.json.JSONObject;

/**
 * Created by sunxiao on 2017/7/4.
 */
public interface HomePageStatisticsService {

    JSONObject memberStatistics() throws Exception;

    JSONObject diabeticStatistics();

    JSONObject hypertensiveStatistics();

    JSONObject doctorStatistics();

    JSONObject nurseStatistics();

}
