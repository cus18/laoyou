/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员用户DAO接口
 * @author 张博
 * @version 2017年5月27日
 */
@MyBatisDao
@Repository
public interface SysElderUserDao {

    int insertSysElderUser(SysElderUserDTO sysElderUserDTO);

    SysElderUserDTO getSysElder(String sysUserID);

    SysElderUserDTO getSysElderUser(String sysElderId);

    Integer updateLoginToken(SysElderUserDTO sysElderUserDTO);

    SysElderUserDTO getSysElderUserByEasemobID(String easemobID);

    String getSysElderUserMemberCardID();

    void delSysElderUser(String id);

    Page getSysElderUserByHospitalID(@Param("sysHospitalID") String sysHospitalID,@Param("search")  String search,Page page);

    Integer getSysElderUserCountByHospitalID(@Param("sysHospitalID") String sysHospitalID);

    Integer getSysElderUserCount();
}
