package com.yhl.laoyou.modules.hospitalService.dao;

import com.yhl.laoyou.common.dto.activity.ActivityDTO;
import com.yhl.laoyou.common.dto.activity.AttendedActivityDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.hospitalService.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@MyBatisDao
@Repository
public interface SysUserRoleDao {

	Integer addSysUserRole(SysUserRole sysUserRole);

	Integer updateSysUserRole(SysUserRole sysUserRole);

}
