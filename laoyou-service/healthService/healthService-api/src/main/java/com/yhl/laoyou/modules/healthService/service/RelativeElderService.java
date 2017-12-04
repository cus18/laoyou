package com.yhl.laoyou.modules.healthService.service;



import com.yhl.laoyou.common.dto.elder.RelativeElderDTO;

import java.util.List;


public interface RelativeElderService {

    List<RelativeElderDTO> relativeElderInfo(String elderID);
}
