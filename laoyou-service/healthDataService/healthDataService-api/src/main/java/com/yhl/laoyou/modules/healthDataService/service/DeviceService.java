package com.yhl.laoyou.modules.healthDataService.service;

import com.yhl.laoyou.common.dto.hospital.DeviceDTO;
import com.yhl.laoyou.common.persistence.Page;
import net.sf.json.JSONArray;


/**
 * Created by sunxiao on 2017/6/20.
 */
public interface DeviceService {

    void operDevice(DeviceDTO deviceDTO) throws Exception;

    void findDeviceList(String param, Page page) throws Exception;

    JSONArray getStatisticsDevice() throws Exception;
}
