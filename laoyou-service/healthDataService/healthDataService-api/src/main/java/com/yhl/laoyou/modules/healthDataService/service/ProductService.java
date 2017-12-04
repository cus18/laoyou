package com.yhl.laoyou.modules.healthDataService.service;

import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;

import java.util.List;

/**
 * Created by sunxiao on 2017/6/26.
 */
public interface ProductService {

    void findProductService(List<HealthServicePackageTemplateDTO> list, HealthServicePackageTemplateDTO dto);

    void operProductService(HealthServicePackageTemplateDTO dto,String oper) throws Exception;
}
