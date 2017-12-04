package com.yhl.laoyou.modules.healthService.service;

import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.dto.PageParamDTO;

import java.util.List;

public interface HealthServicePackage {

    List<HealthServicePackageDTO> getHealthServicePackageList(PageParamDTO<MemberDTO> pageParamDto) throws Exception;

    List<HealthServicePackageTemplateDTO> getHealthServicePackageTemplateList(PageParamDTO pageParamDto) throws Exception;

    HealthServicePackageTemplateDTO getHealthServicePackageTemplateDetail(String healthServicePackageTemplateId) throws Exception;

    HealthServicePackageDTO createHealthServicePackage(HealthServicePackageDTO healthServicePackageDto,String userid) throws Exception;

    HealthServicePackageDTO getOnGoingHealthServicePackage(String healthServicePackageId);

    MemberDTO getElderContactInfo(String elderId);
}
