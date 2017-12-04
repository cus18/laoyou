package com.yhl.laoyou.modules.healthService.service.impl;



import com.yhl.laoyou.common.dto.elder.RelativeElderDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.BasicInfoDTO;
import com.yhl.laoyou.modules.healthService.service.RelativeElderService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by zbm84 on 2017/8/3.
 */
public class RelativeElderServiceImpl implements RelativeElderService {

    private MongoTemplate mongoTemplate;

    @Override
    public List<RelativeElderDTO> relativeElderInfo(String elderID) {
        Query query=new Query().addCriteria(Criteria.where("elderId").is(elderID));
        BasicInfoDTO basicInfoDTO=mongoTemplate.findOne(query, BasicInfoDTO.class,"healthArchive_basicInfo");

        return null;
    }

}
