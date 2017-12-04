package com.yhl.laoyou.modules.healthDataService.service.impl;


import com.yhl.laoyou.common.dto.hospital.DoctorDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysPractitionerUserDTO;
import com.yhl.laoyou.common.dto.practitioner.healthArchive.BasicInfoDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.utils.excel.ImportExcel;
import com.yhl.laoyou.modules.healthDataService.service.DoctorService;
import com.yhl.laoyou.modules.sys.dao.EasemobGroupDao;
import com.yhl.laoyou.modules.sys.dao.SysElderUserDao;
import com.yhl.laoyou.modules.sys.dao.SysPractitionerUserDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.EasemobGroup;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import com.yhl.laoyou.modules.sys.service.UserService;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = false)
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private SysPractitionerUserDao sysPractitionerUserDao;

    @Autowired
    private EasemobGroupDao easemobGroupDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SysElderUserDao sysElderUserDao;

    @Autowired
    private EasemobService easemobService;

    @Autowired
    private UserDao userDao;

    @Override
    public Page getDoctorList(HttpServletRequest request, Page page, String searchValue, String type) {
        User user = UserService.getUser(request);
//        List<DoctorDTO> doctorDTO=sysPractitionerUserDao.getDoctorListByHospitalID(user.getSysHospitalUserDTO().getId());
        Page<DoctorDTO> doctorDTO = sysPractitionerUserDao.getDoctorListByHospitalID(1 + "",page, searchValue, type);
//        page.setCount(sysPractitionerUserDao.getDoctorListCountByHospitalID(user.getSysHospitalUserDTO().getId()));
        for (DoctorDTO d : doctorDTO.getList()) {
            List<EasemobGroup> list = easemobGroupDao.getEasemobGroupByDoctorEasemobID(d.getEasemobID());
            d.setMemberNum(list.size() + "");
            Integer memberNum = 0;
            for (EasemobGroup e : list) {
                SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElderUserByEasemobID(e.getElderEasemobID());
                if(sysElderUserDTO==null){
                    continue;
                }
                List<HealthServicePackageDTO> healthServicePackageDTO = mongoTemplate.find(new Query(Criteria.where("elderId").is(sysElderUserDTO.getId())), HealthServicePackageDTO.class, "healthServicePackage");
                memberNum = healthServicePackageDTO != null && healthServicePackageDTO.size() > 0 ? memberNum + healthServicePackageDTO.size() : memberNum;
            }
            d.setServiceNum(memberNum.toString());
        }
        return doctorDTO;
    }

    @Override
    public String assignedDoctor(String doctorEasemobID, String doctorName, String elderID, String type) {
        EasemobGroup easemobGroup = easemobGroupDao.getEasemobGroupIDByElderID(elderID);
        if (easemobGroup == null) {
            SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElderUser(elderID);
            User user = userDao.get(sysElderUserDTO.getSysUserID());
            String[] array = new String[1];
            array[0] = doctorEasemobID;
            try {
                easemobService.createEasemobGroup(user.getName() + "的医护群", "", false, 200,
                        false, doctorEasemobID, sysElderUserDTO.getEasemobID(), array);
                Query query = new Query(Criteria.where("elderId").is(sysElderUserDTO.getId()));
                BasicInfoDTO b = mongoTemplate.findAndRemove(query, BasicInfoDTO.class, "healthArchive_basicInfo");
                b.setDoctorName(doctorName);
                mongoTemplate.insert(b, "healthArchive_basicInfo");
                return "1";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            try {
                return easemobService.addUserByEasemobGroup(easemobGroup.getEasemobGroupID(), doctorEasemobID, type, type.equals("2")?easemobGroup.getNurse():easemobGroup.getOwner(), elderID, doctorName)?"1":"403";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    @Override
    public void addDoctor(DoctorDTO doctorDTO, HttpServletRequest request) {

        User user = new User();
        user.setLoginName(doctorDTO.getPhone());
        user.setId(UUIDUtil.getUUID());
        user.setPhone(doctorDTO.getPhone());
        user.setCreateDate(new Date());
        user.setArea(doctorDTO.getArea());
        user.setGender(doctorDTO.getGender());
        user.setName(doctorDTO.getName());
//            user.setLoginIp(request.getRemoteAddr());
        userDao.insert(user);
        String source = "practitioner";
        String easemobUserID = source + "_" + user.getId();
        String easemobPassword = UUIDUtil.getUUID();
        try {
            easemobService.signEasemobUser(easemobUserID, easemobPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysPractitionerUserDTO sysPractitionerUser = new SysPractitionerUserDTO();
        sysPractitionerUser.setId(UUIDUtil.getUUID());
        sysPractitionerUser.setSysUserID(user.getId());
        sysPractitionerUser.setEasemobID(easemobUserID);
        sysPractitionerUser.setEasemobPassword(easemobPassword);
        sysPractitionerUser.setType(doctorDTO.getType());
        sysPractitionerUser.setTitle(doctorDTO.getTitle());
        sysPractitionerUser.setDepartment(doctorDTO.getDepartment());
//            sysPractitionerUser.setHospitalName(UserService.getUser(request).getName());
        sysPractitionerUser.setSysHospitalID(UserService.getUser(request).getSysHospitalUserDTO().getSysOfficeID());
        sysPractitionerUser.setSysHospitalID("1");
        sysPractitionerUserDao.insertSysPractitionerUser(sysPractitionerUser);
    }


    @Override
    public void batchAddDoctor(File file, HttpServletRequest request) throws Exception {
        ImportExcel ie = new ImportExcel(file, 1);
        List<DoctorDTO> list = ie.getDataList(DoctorDTO.class);
        for (DoctorDTO b : list) {
            if(b.getPhone()==null||b.getPhone().equals("")){
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
            user.setArea(b.getArea());
            user.setGender(b.getGender().equals("男")?"1":"2");
            user.setName(b.getName());
//            user.setLoginIp(request.getRemoteAddr());
            userDao.insert(user);
            String source = "practitioner";
            String easemobUserID = source + "_" + user.getId();
            String easemobPassword = UUIDUtil.getUUID();
            easemobService.signEasemobUser(easemobUserID, easemobPassword);
            SysPractitionerUserDTO sysPractitionerUser = new SysPractitionerUserDTO();
            sysPractitionerUser.setId(UUIDUtil.getUUID());
            sysPractitionerUser.setSysUserID(user.getId());
            sysPractitionerUser.setEasemobID(easemobUserID);
            sysPractitionerUser.setEasemobPassword(easemobPassword);
            sysPractitionerUser.setType(b.getType().equals("医生") ? "1" : "2");
            sysPractitionerUser.setTitle(b.getTitle());
            sysPractitionerUser.setDepartment(b.getDepartment());
//            sysPractitionerUser.setHospitalName(UserService.getUser(request).getName());
            sysPractitionerUser.setSysHospitalID(UserService.getUser(request).getSysHospitalUserDTO().getSysOfficeID());
            sysPractitionerUser.setSysHospitalID("1");
            sysPractitionerUserDao.insertSysPractitionerUser(sysPractitionerUser);
        }
    }

    @Override
    public DoctorDTO getDoctor(String id) {
        DoctorDTO doctorDTO = new DoctorDTO();
        SysPractitionerUserDTO sysPractitionerUserDTO = sysPractitionerUserDao.getSysPractitionerByID(id);
        User user = userDao.get(sysPractitionerUserDTO.getSysUserID());
        doctorDTO.setId(sysPractitionerUserDTO.getId());
        doctorDTO.setName(user.getName());
        doctorDTO.setArea(user.getArea());
        doctorDTO.setGender(user.getGender());
        doctorDTO.setPhone(user.getPhone());
        doctorDTO.setTitle(sysPractitionerUserDTO.getTitle());
        doctorDTO.setDepartment(sysPractitionerUserDTO.getDepartment());
        doctorDTO.setType(sysPractitionerUserDTO.getType().toString());
        return doctorDTO;
    }

    @Override
    public void updateDoctor(DoctorDTO doctorDTO) throws Exception {
        SysPractitionerUserDTO sysPractitionerUserDTO = sysPractitionerUserDao.getSysPractitionerByID(doctorDTO.getId());
        if (!sysPractitionerUserDTO.getDepartment().equals(doctorDTO.getDepartment())
                && !sysPractitionerUserDTO.getTitle().equals(doctorDTO.getTitle())
                && !sysPractitionerUserDTO.getType().equals(doctorDTO.getType())) {
            sysPractitionerUserDTO.setDepartment(doctorDTO.getDepartment());
            sysPractitionerUserDTO.setTitle(doctorDTO.getTitle());
            sysPractitionerUserDTO.setType(doctorDTO.getType());
            sysPractitionerUserDao.updateSysPractitionerUser(sysPractitionerUserDTO);
        }
        User user = userDao.get(sysPractitionerUserDTO.getSysUserID());
        if (user.getName().equals(doctorDTO.getName())
                && user.getGender().equals(doctorDTO.getGender())
                && user.getArea().equals(doctorDTO.getArea())
                && user.getPhone().equals(doctorDTO.getPhone())) {
            return;
        } else {
            user = new User();
            user.setName(doctorDTO.getName());
            user.setPhone(doctorDTO.getPhone());
            user.setGender(doctorDTO.getGender());
            user.setArea(doctorDTO.getArea());
            user.setId(sysPractitionerUserDTO.getSysUserID());
            if (userDao.updateUser(user) > 0) {
                if (easemobService.updateEasemobUserNickName(sysPractitionerUserDTO.getEasemobID(), doctorDTO.getName())) {
                    return;
                }
            }
        }
    }

    @Override
    public void delDoctor(String id) {
        SysPractitionerUserDTO sysPractitionerUserDTO=sysPractitionerUserDao.getSysPractitionerByID(id);
        userDao.delete(sysPractitionerUserDTO.getSysUserID());
        List<EasemobGroup> easemobGroup=easemobGroupDao.getEasemobGroupByDoctorEasemobID(sysPractitionerUserDTO.getEasemobID());
        if(easemobGroup==null){
            return;
        }
    }
}
