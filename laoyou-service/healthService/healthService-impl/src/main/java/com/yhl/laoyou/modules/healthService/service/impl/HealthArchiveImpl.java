package com.yhl.laoyou.modules.healthService.service.impl;

import com.yhl.laoyou.common.dto.GroupMemberDTO;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.elder.RelativeElderDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.*;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.*;
import com.yhl.laoyou.common.utils.excel.ImportExcel;
import com.yhl.laoyou.modules.healthService.service.HealthArchive;
import com.yhl.laoyou.modules.notificationRemindService.dao.RemindTemplateDao;
import com.yhl.laoyou.modules.sys.dao.EasemobGroupDao;
import com.yhl.laoyou.modules.sys.dao.SysElderUserDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;
import com.yhl.laoyou.modules.sys.entity.PaginationVo;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.UserService;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@Transactional(readOnly = false)
public class HealthArchiveImpl implements HealthArchive {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    EasemobService easemobService;

    @Autowired
    UserService userService;

    @Autowired
    private SysElderUserDao sysElderUserDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EasemobGroupDao easemobGroupDao;

    @Autowired
    private RemindTemplateDao remindTemplateDao;

    private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
    private User user;

    public void sendEasemobMessage(String easemobGroup, String message) {
        EasemobService.sendEasemobMessage(easemobGroup, message);
    }

    public class sendEasemobMessage extends Thread {

        private String easemobGroup;
        private String message;

        public sendEasemobMessage(String easemobGroup, String message) {
            this.easemobGroup = easemobGroup;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                sendEasemobMessage(easemobGroup, message);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public Page getElderBasicInfoList(String id, Page page, String search) {
        Integer a=( page.getPageNo()-1 )* page.getPageSize();
        Page<SysElderUserDTO> list = sysElderUserDao.getSysElderUserByHospitalID(id,search,page);
        Page<MemberDTO> returnPage= new Page<>();
        List<MemberDTO> listB=new ArrayList<>();
        for (SysElderUserDTO s : list.getList()) {
            MemberDTO memberDTO = new MemberDTO();
            BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
            search = Encodes.urlDecode(search);
            if (!search.equals("") && search != null) {
                try {
                    search = URLDecoder.decode(search, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Criteria criteria = new Criteria();
                criteria.orOperator(Criteria.where("elderName").regex(".*?" + search + ".*")
                        , Criteria.where("age").regex(".*?" + search + ".*")
                        , Criteria.where("healthServiceName").regex(".*?" + search + ".*")
                        , Criteria.where("doctorName").regex(".*?" + search + ".*")
                        , Criteria.where("nurseName").regex(".*?" + search + ".*"));
                basicInfoDTO = this.mongoTemplate.findOne(new Query(Criteria.where("elderId").is(s.getId()).andOperator(criteria)), BasicInfoDTO.class, "healthArchive_basicInfo");
            } else {
                basicInfoDTO = this.mongoTemplate.findOne(new Query(Criteria.where("elderId").is(s.getId())), BasicInfoDTO.class, "healthArchive_basicInfo");
            }
//            if (basicInfoDTO != null) {
//                memberDTO.setMemberName(basicInfoDTO.getElderName());
//                memberDTO.setElderId(basicInfoDTO.getElderId());
//                memberDTO.setMemberCardID(basicInfoDTO.getMemberCarID());
//            } else {
//                memberDTO.setMemberName("");
//                memberDTO.setElderId("");
//                memberDTO.setMemberCardID("");
//                continue;
//            }
            User user=userDao.get(s.getSysUserID());
            memberDTO.setMemberName(user.getName());
            memberDTO.setElderId(s.getId());
            memberDTO.setMemberCardID(s.getMemberCardID());
            memberDTO.setAge(user.getAge());
            EasemobGroup easemobGroup = easemobGroupDao.getEasemobGroupIDByElderID(s.getId());
            if (easemobGroup != null) {
                GroupMemberDTO groupMemberDTO = easemobService.getEasemobGroupByGroupID(easemobGroup.getEasemobGroupID());
                memberDTO.setDoctorName(groupMemberDTO.getOwnerName());
                memberDTO.setNurseName(groupMemberDTO.getNurseName());
//                if(groupMemberDTO.getGroupDoctorDTOList()!=null) {
//                    for (GroupDoctorDTO groupDoctorDTO : groupMemberDTO.getGroupDoctorDTOList()) {
//                        memberDTO.setNurseName(memberDTO.getNurseName() == null ? groupDoctorDTO.getDoctorName() + ";" : memberDTO.getNurseName() + groupDoctorDTO.getDoctorName() + ";");
//                    }
//                }
            }
            Query query = new Query(Criteria.where("elderId").is(s.getId()));
            List<HealthServicePackageDTO> data = this.mongoTemplate.find(query, HealthServicePackageDTO.class, "healthServicePackage");
            if (data != null) {
                String templateName = "";
                if (data != null || data.size() > 0) {
                    for (HealthServicePackageDTO healthServicePackageDTO : data) {
                        templateName += healthServicePackageDTO.getServicePackageTemplateName() + ";";
                    }
                }
                memberDTO.setMemberExtendData(templateName);
            }
            listB.add(memberDTO);
        }
        returnPage.setPageSize(page.getPageSize());
        returnPage.setPageNo(page.getPageNo());
        returnPage.setCount(page.getCount());
        returnPage.setList(listB);
        return returnPage;
    }

    @Override
    public BasicInfoDTO getElderBasicInfo(String elderId){
        Query query = new Query(Criteria.where("elderId").is(elderId));
        BasicInfoDTO data = this.mongoTemplate.findOne(query, BasicInfoDTO.class, "healthArchive_basicInfo");
//        data.get(0).setBirthday(data.get(0).getBirthday());
        return data;
    }

    @Override
    public List<PhysicalExaminationDTO> getPhysicalExaminationList(PageParamDTO<MemberDTO> pageParamDto) throws Exception {

        Query query = null;
        if (pageParamDto.getOrderType().equals("1")) {
            if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId())).with(new Sort(Sort.Direction.DESC, "updateDate"));
            } else if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId())).with(new Sort(Sort.Direction.ASC, "updateDate"));
            }
        }

        long totalCount = this.mongoTemplate.count(query, "healthArchive_physicalExamination");
        PaginationVo<PhysicalExaminationDTO> page = new PaginationVo<>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<PhysicalExaminationDTO> data = this.mongoTemplate.find(query, PhysicalExaminationDTO.class, "healthArchive_physicalExamination");

        /*for(PhysicalExaminationDTO physicalExaminationDto : data)
        {
            physicalExaminationDto.setUpdateDate(TimeUtils.formatTimeEight(physicalExaminationDto.getUpdateDate()));
        }*/

        return data;
    }

    @Override
    public List<HealthAssessmentDTO> getHealthAssessmentList(PageParamDTO<MemberDTO> pageParamDto) throws Exception {
        Query query = null;
        if (pageParamDto.getOrderType().equals("1")) {
            if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId())).with(new Sort(Sort.Direction.DESC, "updateDate"));
            } else if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query(where("elderId").is(pageParamDto.getRequestData().getElderId())).with(new Sort(Sort.Direction.ASC, "updateDate"));
            }

        }

        long totalCount = this.mongoTemplate.count(query, "healthArchive_healthAssessment");
        PaginationVo<HealthAssessmentDTO> page = new PaginationVo<>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<HealthAssessmentDTO> data = this.mongoTemplate.find(query, HealthAssessmentDTO.class, "healthArchive_healthAssessment");

        for (HealthAssessmentDTO healthAssessmentDto : data) {
            healthAssessmentDto.setUpdateDate(TimeUtils.formatTimeEight(healthAssessmentDto.getUpdateDate()));
        }

        return data;
    }

    @Override
    public HealthAssessmentDTO getHealthAssessment(String healthAssessmentId, String keyId) throws Exception {

        Query query = new Query(where("healthAssessmentTemplateId").is(healthAssessmentId)).
                addCriteria(Criteria.where("_id").is(keyId));
        List<HealthAssessmentDTO> data = this.mongoTemplate.find(query, HealthAssessmentDTO.class, "healthArchive_healthAssessment");
        data.get(0).setUpdateDate(TimeUtils.formatTimeEight(data.get(0).getUpdateDate()));
        return data.get(0);
    }

    @Override
    public List<HealthAssessmentTemplateDTO> GetHealthArchiveHealthAssessmentTemplateList(PageParamDTO pageParamDto) throws Exception {
        Query query = null;
        if (pageParamDto.getOrderType().equals("1")) {
            if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query().with(new Sort(Sort.Direction.DESC, "updateDate"));
            } else if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query().with(new Sort(Sort.Direction.ASC, "updateDate"));
            }

        }

        long totalCount = this.mongoTemplate.count(query, "healthArchive_healthAssessmentTemplate");
        PaginationVo<HealthAssessmentTemplateDTO> page = new PaginationVo<>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<HealthAssessmentTemplateDTO> data = this.mongoTemplate.find(query, HealthAssessmentTemplateDTO.class, "healthArchive_healthAssessmentTemplate");

        for (HealthAssessmentTemplateDTO healthAssessmentTemplateDto : data) {
            healthAssessmentTemplateDto.setUpdateDate(TimeUtils.formatTimeEight(healthAssessmentTemplateDto.getUpdateDate()));
        }

        return data;
    }

    @Override
    public List<PhysicalExaminationTemplateDTO> getPhysicalExaminationTemplateList(PageParamDTO pageParamDto) throws Exception {
        Query query = null;
        if (pageParamDto.getOrderType().equals("1")) {
            if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query().with(new Sort(Sort.Direction.DESC, "updateDate"));
            } else if (pageParamDto.getOrderBy().equals("0")) {
                query = new Query().with(new Sort(Sort.Direction.ASC, "updateDate"));
            }

        }

        long totalCount = this.mongoTemplate.count(query, "healthArchive_physicalExaminationTemplate");
        PaginationVo<PhysicalExaminationTemplateDTO> page = new PaginationVo<>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        List<PhysicalExaminationTemplateDTO> data = this.mongoTemplate.find(query, PhysicalExaminationTemplateDTO.class, "healthArchive_physicalExaminationTemplate");

        for (PhysicalExaminationTemplateDTO physicalExaminationTemplateDto : data) {
            physicalExaminationTemplateDto.setUpdateDate(TimeUtils.formatTimeEight(physicalExaminationTemplateDto.getUpdateDate()));
        }

        return data;
    }

    @Override
    public PhysicalExaminationDTO getPhysicalExamination(String physicalExaminationId) throws Exception {
        Query query = new Query(where("physicalExaminationId").is(physicalExaminationId));
        List<PhysicalExaminationDTO> data = this.mongoTemplate.find(query, PhysicalExaminationDTO.class, "healthArchive_physicalExamination");
        //data.get(0).setUpdateDate(data.get(0).getUpdateDate());
        return data.get(0);
    }

    @Override
    public PhysicalExaminationDTO createPhysicalExamination(PhysicalExaminationDTO physicalExaminationDTO) throws Exception {

        physicalExaminationDTO.setPhysicalExaminationId(UUID.randomUUID().toString());
        physicalExaminationDTO.setUpdateDate(DateUtils.DateToStr(new Date(), "datetime"));
        try {
            this.mongoTemplate.insert(physicalExaminationDTO, "healthArchive_physicalExamination");
        } catch (Exception e) {
            throw new Exception("create healthServicePackage failure");
        }

        try {
            String message = easemobService.getEasemobMessageUrl("chatType2", physicalExaminationDTO.getPhysicalExaminationId());

            EasemobGroup easemobGroup = easemobService.getEasemobGroup(physicalExaminationDTO.getElderId());
            Runnable thread = new sendEasemobMessage(easemobGroup.getEasemobGroupID(), message);
            threadExecutor.execute(thread);
        } catch (Exception e) {

        }

        return physicalExaminationDTO;
    }

    @Override
    public void createHealthAssessment(HealthAssessmentDTO healthAssessmentAnswer) throws Exception {
        healthAssessmentAnswer.setUpdateDate(new Date());
        try {
            this.mongoTemplate.insert(healthAssessmentAnswer, "healthArchive_healthAssessment");
        } catch (Exception e) {
            throw new Exception("create healthServicePackage failure");
        }

    }

    @Override
    public void addElderBasicInfo(BasicInfoDTO basicInfoDTO, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setLoginName(basicInfoDTO.getPhone());
        user.setId(UUIDUtil.getUUID());
        user.setPhone(basicInfoDTO.getPhone());
        user.setCreateDate(new Date());
        user.setLoginIp(request.getRemoteAddr());
        user.setName(basicInfoDTO.getElderName());
        user.setGender(basicInfoDTO.getGender());
        long age = (new Date().getTime() - basicInfoDTO.getBirthday().getTime()) / 1000 / 60 / 60 / 24 / 365;
        user.setAge(String.valueOf(age == 0 ? 1 : age));
        userService.insertUser(user);
        user.setSource("elder");
        String easemobUserID = "elder_" + user.getId();
        String easemobPassword = UUIDUtil.getUUID();
        easemobService.signEasemobUser(easemobUserID, easemobPassword);
        SysElderUserDTO sysElderUserDTO = new SysElderUserDTO();
        sysElderUserDTO.setId(UUIDUtil.getUUID());
        sysElderUserDTO.setSysUserID(user.getId());
        sysElderUserDTO.setEasemobPassword(easemobPassword);
        sysElderUserDTO.setEasemobID(easemobUserID);
        sysElderUserDTO.setMemberCardID(sysElderUserDao.getSysElderUserMemberCardID() == null ? "10000000" : Integer.parseInt(sysElderUserDao.getSysElderUserMemberCardID()) + 1 + "");
//        sysElderUserDTO.setSysHospitalID(UserService.getUser(request).getSysHospitalUserDTO().getId());
        sysElderUserDTO.setSysHospitalID(UserService.getUser(request).getSysHospitalUserDTO().getSysOfficeID());
//        sysElderUserDTO.setSysHospitalID("1");
        sysElderUserDao.insertSysElderUser(sysElderUserDTO);
        basicInfoDTO.setElderId(sysElderUserDTO.getId());
        basicInfoDTO.setHealthArchiveId(UUIDUtil.getUUID());
        basicInfoDTO.setHealthServiceName("");
        basicInfoDTO.setAge(String.valueOf(age == 0 ? 1 : age));
        basicInfoDTO.setDoctorName("");
        basicInfoDTO.setNurseName("");
        basicInfoDTO.setMemberCarID(sysElderUserDTO.getMemberCardID());
        this.mongoTemplate.insert(basicInfoDTO, "healthArchive_basicInfo");
        //添加亲友
//        for (RelativeDTO r:basicInfoDTO.getRelativeList()) {
//            r.setElderId(sysElderUserDTO.getId());
//            this.mongoTemplate.insert(r, "healthArchive_basicInfo_relative");
//        }
        String content=remindTemplateDao.getRemindTemplateEntityByID("2").getContent();
        EasemobService.sendEasemobMessage(sysElderUserDTO.getEasemobID(),content,"users");
    }

    @Override
    public void batchAddElderBasicInfo(File file, HttpServletRequest request) throws Exception {
        ImportExcel ie = new ImportExcel(file, 1);
        List<BasicInfoDTO> list = ie.getDataList(BasicInfoDTO.class);
        for (BasicInfoDTO b : list) {
            if (b.getPhone() == null || b.getPhone().equals("")) {
                continue;
            }
            DecimalFormat df = new DecimalFormat("0");
            Number i = df.parse(b.getPhone());
            b.setPhone(df.format(i));
            User user = new User();
            user.setLoginName(b.getPhone());
            user.setId(UUIDUtil.getUUID());
            user.setPhone(b.getPhone());
            user.setCreateDate(new Date());
            user.setLoginIp(request.getRemoteAddr());
            user.setName(b.getElderName());
            user.setGender(b.getGender());
            userService.insertUser(user);
            user.setSource("elder");
            String easemobUserID = "elder_" + user.getId();
            String easemobPassword = UUIDUtil.getUUID();
            easemobService.signEasemobUser(easemobUserID, easemobPassword);
            SysElderUserDTO sysElderUserDTO = new SysElderUserDTO();
            sysElderUserDTO.setId(UUIDUtil.getUUID());
            sysElderUserDTO.setSysUserID(user.getId());
            sysElderUserDTO.setEasemobPassword(easemobPassword);
            sysElderUserDTO.setEasemobID(easemobUserID);
//            sysElderUserDTO.setSysHospitalID(UserService.getUser(request).getSysHospitalUserDTO().getId());
            sysElderUserDTO.setSysHospitalID("1");
            sysElderUserDTO.setMemberCardID(sysElderUserDao.getSysElderUserMemberCardID() == null ? "10000000" : Integer.parseInt(sysElderUserDao.getSysElderUserMemberCardID()) + 1+"");
            sysElderUserDao.insertSysElderUser(sysElderUserDTO);
            b.setElderId(sysElderUserDTO.getId());
            b.setHealthArchiveId(UUIDUtil.getUUID());
            b.setHealthServiceName("");
            long age=(new Date().getTime()-b.getBirthday().getTime())/60/60/24/365/1000;
            b.setAge(String.valueOf(age));
            b.setDoctorName("");
            b.setNurseName("");
            b.setMemberCarID(sysElderUserDTO.getMemberCardID());
            this.mongoTemplate.insert(b, "healthArchive_basicInfo");
        }
    }

    @Override
    public void updateElderBasicInfo(BasicInfoDTO basicInfoDTO) throws Exception {
        Query query = new Query(Criteria.where("healthArchiveId").is(basicInfoDTO.getHealthArchiveId()));
        BasicInfoDTO b = mongoTemplate.findAndRemove(query, BasicInfoDTO.class, "healthArchive_basicInfo");
        basicInfoDTO.setElderId(b.getElderId());
        mongoTemplate.insert(basicInfoDTO, "healthArchive_basicInfo");
        if (b.getElderName().equals(basicInfoDTO.getElderName()) && b.getPhone().equals(basicInfoDTO.getPhone()) && b.getGender().equals(basicInfoDTO.getGender())) {
            return;
        } else {
            User user = new User();
            user.setName(basicInfoDTO.getElderName());
            user.setPhone(basicInfoDTO.getPhone());
            user.setGender(basicInfoDTO.getGender());
            user.setId(sysElderUserDao.getSysElderUser(b.getElderId()).getSysUserID());
            if (userService.updateUser(user)) {
                if (easemobService.updateEasemobUserNickName(user.getSysPractitionerUser().getEasemobID(), basicInfoDTO.getElderName())) {
                    return;
                }
            }
        }
    }

    @Override
    public void delElderBasicInfo(String id, HttpServletRequest request) throws Exception {
        SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElderUser(id);
        User user = userDao.get(sysElderUserDTO.getSysUserID());
        userDao.delete(user.getId());
    }

    @Override
    public List<RelativeElderDTO> getRelativeList(User user) {
        String elderId=user.getSysElderUserDTO().getId();
        List<RelativeElderDTO> l=new ArrayList<>();
//        Query query=new Query(Criteria.where("elderId").is(elderID));
//        List<RelativeDTO> list=this.mongoTemplate.find(query,RelativeDTO.class,"healthArchive_basicInfo_relative");
//        for (RelativeDTO r:list) {
//            User user=new User();
//            user.setLoginName(r.getPhone());
//            user=userDao.getByLoginName(user);
//            SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElder(user.getId());
//            Query querys = new Query(Criteria.where("elderId").is(sysElderUserDTO.getId()));
//            BasicInfoDTO data = this.mongoTemplate.findOne(querys, BasicInfoDTO.class, "healthArchive_basicInfo");
//            RelativeElderDTO re = new RelativeElderDTO();
//            re.setGender(user.getGender());
//            re.setAge(data.getAge());
//            re.setElderID(sysElderUserDTO.getId());
//            re.setHeadImage(user.getPhoto());
//            re.setIdCard(data.getIdNum().substring(0,3)+"*********"+data.getIdNum().substring(12));
//            re.setPhone(data.getPhone().substring(0,3)+"********"+data.getPhone().substring(8));
//            re.setElderName(data.getElderName());
//            l.add(re);
//        }

        RelativeElderDTO relativeElderDTO = new RelativeElderDTO();
//        Query querys = new Query(Criteria.where("elderId").is(elderID));
//        BasicInfoDTO data = this.mongoTemplate.findOne(querys, BasicInfoDTO.class, "healthArchive_basicInfo");
        relativeElderDTO.setGender(user.getGender());
        relativeElderDTO.setAge(user.getAge());
        relativeElderDTO.setElderID(user.getSysElderUserDTO().getId());
        relativeElderDTO.setElderName(user.getName());
        l.add(relativeElderDTO);

        return l;
    }


}
