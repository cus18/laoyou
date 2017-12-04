/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.dto.hospital.DoctorDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysPractitionerUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 医护用户DAO接口
 *
 * @author
 * @version 2017年5月9日
 */
@MyBatisDao
@Repository
public interface SysPractitionerUserDao {

    int insertSysPractitionerUser(SysPractitionerUserDTO sysPractitionerUser);

    int updateSysPractitionerUser(SysPractitionerUserDTO sysPractitionerUser);

    SysPractitionerUserDTO getSysPractitioner(String sysUserID);

    Integer updateLoginToken(SysPractitionerUserDTO sysPractitionerUser);

    SysPractitionerUserDTO getSysPractitionerByEasemobID(String easemobID);

    SysPractitionerUserDTO getSysPractitionerByID(String id);

    Page getDoctorListByHospitalID(@Param("sysHospitalID") String sysHospitalID,Page page,@Param("searchValue") String searchValue, @Param("type") String type);

    Integer getDoctorListCountByHospitalID(@Param("sysHospitalID") String sysHospitalID);

    List<String> doctorAndNurseStatistics(String title);
}
