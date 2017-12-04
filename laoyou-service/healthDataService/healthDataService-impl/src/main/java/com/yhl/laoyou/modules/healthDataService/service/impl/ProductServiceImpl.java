package com.yhl.laoyou.modules.healthDataService.service.impl;

import com.yhl.laoyou.common.constant.OperationType;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;
import com.yhl.laoyou.modules.healthDataService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by sunxiao on 2017/6/26.
 */

@Service
@Transactional(readOnly = false)
public class ProductServiceImpl implements ProductService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void findProductService(List<HealthServicePackageTemplateDTO> list, HealthServicePackageTemplateDTO dto) {
        List<HealthServicePackageTemplateDTO> templateData = new ArrayList<>();
        if(dto.getHealthServicePackageTemplateId()==null) {
            templateData = this.mongoTemplate.find(new Query(), HealthServicePackageTemplateDTO.class, "healthServicePackage_template");
        } else {
            Query query = new Query(where("healthServicePackageTemplateId").is(dto.getHealthServicePackageTemplateId()));
            templateData = this.mongoTemplate.find(query,HealthServicePackageTemplateDTO.class,"healthServicePackage_template");
        }
        for(HealthServicePackageTemplateDTO temp : templateData){
            Query query = new Query(where("servicePackageTemplateId").is(temp.getHealthServicePackageTemplateId()));
            Long count =  this.mongoTemplate.count(query, HealthServicePackageDTO.class, "healthServicePackage");
            temp.setSubscribeNum(count.toString());
            list.add(temp);
        }
    }

    @Override
    public void operProductService(HealthServicePackageTemplateDTO dto,String oper) throws Exception {
        if(OperationType.ADD.getValue().equals(oper)){
            dto.setHealthServicePackageTemplateId(UUID.randomUUID().toString());
            dto.setUpdateDate(new Date());
            dto.setFirstPartySignature("/images/seal_11.png");
            this.mongoTemplate.insert(dto,"healthServicePackage_template");
        }else if(OperationType.UPDATE.getValue().equals(oper)){
            Query query = new Query(where("servicePackageTemplateId").is(dto.getHealthServicePackageTemplateId()));
            List<HealthServicePackageDTO> templist = this.mongoTemplate.find(query,HealthServicePackageDTO.class,"healthServicePackage");
            if(templist != null && templist.size()>0){
                throw new Exception("该模板绑定用户，无法修改！");
            }else{
                query = new Query(where("healthServicePackageTemplateId").is(dto.getHealthServicePackageTemplateId()));
                this.mongoTemplate.findAndRemove(query,dto.getClass(),"healthServicePackage_template");
                dto.setUpdateDate(new Date());
                dto.setFirstPartySignature("/images/seal_11.png");
                this.mongoTemplate.insert(dto,"healthServicePackage_template");
            }
        }else if(OperationType.DELETE.getValue().equals(oper)){
            Query query = new Query(where("servicePackageTemplateId").is(dto.getHealthServicePackageTemplateId()));
            List<HealthServicePackageDTO> templist = this.mongoTemplate.find(query,HealthServicePackageDTO.class,"healthServicePackage");
            if(templist != null && templist.size()>0){
                throw new Exception("该模板绑定用户，无法删除！");
            }else{
                query = new Query(where("healthServicePackageTemplateId").is(dto.getHealthServicePackageTemplateId()));
                this.mongoTemplate.findAndRemove(query,dto.getClass(),"healthServicePackage_template");
            }
        }
    }
}
