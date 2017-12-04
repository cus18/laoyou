package com.yhl.laoyou.modules.healthService.service.impl;

import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.BasicInfoDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.utils.TimeUtils;
import com.yhl.laoyou.modules.healthService.service.HealthServicePackage;
import com.yhl.laoyou.modules.sys.dao.SysHospitalUserDao;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;
import com.yhl.laoyou.modules.sys.entity.PaginationVo;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@Transactional(readOnly = false)
public class HealthServicePackageImpl implements HealthServicePackage {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    EasemobService easemobService;

    @Autowired
    UserService userService;

    @Autowired
    SysHospitalUserDao sysHospitalUserDao;

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    public void sendEasemobMessage(String easemobGroup,String message) {
        EasemobService.sendEasemobMessage(easemobGroup,message);
    }

    public class sendEasemobMessage extends Thread {

        private String easemobGroup;
        private String message;

        public sendEasemobMessage(String easemobGroup,String message) {
            this.easemobGroup = easemobGroup;
            this.message = message;
        }

        @Override
        public void run() {
            sendEasemobMessage(easemobGroup,message);
        }
    }

    @Override
    public List<HealthServicePackageDTO> getHealthServicePackageList(PageParamDTO<MemberDTO> pageParamDto) throws Exception {

        Query query = null;
        if(pageParamDto.getOrderType().equals("1"))
        {
            if(pageParamDto.getOrderBy().equals("0"))
            {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId()).andOperator(where("status").is("ongoing"))).with(new Sort(Sort.Direction.DESC, "updateDate"));
            }
            else if(pageParamDto.getOrderBy().equals("0"))
            {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId()).andOperator(where("status").is("ongoing"))).with(new Sort(Sort.Direction.ASC, "updateDate"));
            }

        }

        long totalCount = this.mongoTemplate.count(query, "healthServicePackage");
        PaginationVo<HealthServicePackageTemplateDTO> page = new PaginationVo<HealthServicePackageTemplateDTO>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<HealthServicePackageDTO> data = this.mongoTemplate.find(query, HealthServicePackageDTO.class, "healthServicePackage");

        for(HealthServicePackageDTO healthServicePackageDto : data)
        {
            healthServicePackageDto.setUpdateDate(TimeUtils.formatTimeEight(healthServicePackageDto.getUpdateDate()));
        }

        return data;
    }

    @Override
    public List<HealthServicePackageTemplateDTO> getHealthServicePackageTemplateList(PageParamDTO pageParamDto) throws Exception {

        Query query = null;
        if(pageParamDto.getOrderType().equals("1"))
        {
            if(pageParamDto.getOrderBy().equals("0"))
            {
                query = new Query().with(new Sort(Sort.Direction.DESC, "updateDate"));
            }
            else if(pageParamDto.getOrderBy().equals("0"))
            {
                query = new Query().with(new Sort(Sort.Direction.ASC, "updateDate"));
            }

        }

        long totalCount = this.mongoTemplate.count(query, "healthServicePackage_template");
        PaginationVo<HealthServicePackageTemplateDTO> page = new PaginationVo<HealthServicePackageTemplateDTO>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<HealthServicePackageTemplateDTO> data = this.mongoTemplate.find(query, HealthServicePackageTemplateDTO.class, "healthServicePackage_template");

        for(HealthServicePackageTemplateDTO healthServicePackageTemplateDto : data)
        {
            healthServicePackageTemplateDto.setUpdateDate(TimeUtils.formatTimeEight(healthServicePackageTemplateDto.getUpdateDate()));
        }

        return data;
    }

    @Override
    public HealthServicePackageTemplateDTO getHealthServicePackageTemplateDetail(String healthServicePackageTemplateId) throws Exception {

        Query query = new Query(where("healthServicePackageTemplateId").is(healthServicePackageTemplateId));
        List<HealthServicePackageTemplateDTO> data = this.mongoTemplate.find(query, HealthServicePackageTemplateDTO.class, "healthServicePackage_template");
//        data.get(0).setHealthServicePackageTemplateData(FileUtils.txt2String(data.get(0).getHealthServicePackageTemplateData().toString()));
        return data.get(0);
    }

    @Override
    public HealthServicePackageDTO createHealthServicePackage(HealthServicePackageDTO healthServicePackageDto,String userId) throws Exception {
        //查询机构编号
        SysHospitalUserDTO sysHospitalUserDTO = new SysHospitalUserDTO();
        sysHospitalUserDTO.setSysUserID(userId);
        List<SysHospitalUserDTO> list = sysHospitalUserDao.getSysHospitalUserByInfo(sysHospitalUserDTO);
        String contractNo = "1000";
        if(list!=null && list.size()>0){
            contractNo = String.format("%04d", Integer.parseInt(list.get(0).getSysOfficeID()));
        }
        //查询个人编号
        Query query = new Query().addCriteria(Criteria.where("contractNo").regex(".*?" + contractNo + ".*"));
        long count = mongoTemplate.count(query,"healthServicePackage");

        healthServicePackageDto.setContractNo(contractNo + "-" + String.format("%05d", count));//合同编号命名规则: 100-00001 100-00002 前三位为机构编码，后五位为个人编码
        healthServicePackageDto.setServicePackageId(UUID.randomUUID().toString());
        healthServicePackageDto.setStatus("ongoing");
        healthServicePackageDto.setUpdateDate(new Date());
        try
        {
            this.mongoTemplate.insert(healthServicePackageDto,"healthServicePackage");
//            Query query=new Query(Criteria.where("elderId").is(healthServicePackageDto.getElderId()));
//            BasicInfoDTO basicInfoDTO=this.mongoTemplate.findOne(query,BasicInfoDTO.class,"healthArchive_basicInfo");
//            Update update=new Update();
//            update.set("healthServiceName",basicInfoDTO.getHealthServiceName()==null?healthServicePackageDto.getServicePackageTemplateName()+";":basicInfoDTO.getHealthServiceName()+healthServicePackageDto.getServicePackageTemplateName());
//            mongoTemplate.updateFirst(query,update,"healthArchive_basicInfo");
        }
        catch (Exception e)
        {
            throw new Exception("create healthServicePackage failure");
        }
        String message = easemobService.getEasemobMessageUrl("chatType1",healthServicePackageDto.getServicePackageId());

        EasemobGroup easemobGroup = easemobService.getEasemobGroup(healthServicePackageDto.getElderId());
        Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(),message);
        threadExecutor.execute(thread);

        return healthServicePackageDto;
    }

    @Override
    public HealthServicePackageDTO getOnGoingHealthServicePackage(String healthServicePackageId) {
        Query query = new Query(where("servicePackageId").is(healthServicePackageId));
        List<HealthServicePackageDTO> data = this.mongoTemplate.find(query, HealthServicePackageDTO.class, "healthServicePackage");
        return data.get(0);
    }

    @Override
    public MemberDTO getElderContactInfo(String elderId) {
        MemberDTO memberDto = new MemberDTO();
        Query query = new Query(where("elderId").is(elderId));
        List<BasicInfoDTO> data = this.mongoTemplate.find(query, BasicInfoDTO.class, "healthArchive_basicInfo");
        memberDto.setElderId(data.get(0).getElderId());
        memberDto.setMemberName(data.get(0).getElderName());
        String memberExtendData = null;
        HashMap<String,Object> memberExtendValue = new HashMap<>();
        memberExtendValue.put("mobile", "13121278331");
        memberExtendValue.put("telephone", "01057879932");
        memberExtendValue.put("address","北京市朝阳区望京小区");
        memberExtendValue.put("emergencyContactName", "刘涛");
        memberExtendValue.put("emergencyContactPhone", "13238492991");
        JSONObject jsonObject = JSONObject.fromObject(memberExtendValue);
        memberExtendData = jsonObject.toString();
        memberDto.setMemberExtendData(memberExtendData);
        return memberDto;
    }

}
