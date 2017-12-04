package com.yhl.laoyou.modules.hospitalService.service;


import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.DietPlanDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.sys.entity.Office;

import java.util.List;


public interface OfficeService {

    Integer  insertOffice(Office office);

    Page getOfficeList(String searchValue,Page page);

    Integer updateOffice(Office office);

    Integer deleteOffice(Office office);

    Office getOfficeByID(String ID);
}
