/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.dto.APPVersionDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 会员用户DAO接口
 * @author 张博
 * @version 2017年5月27日
 */
@MyBatisDao
@Repository
public interface APPVersionDao {

    int insert(APPVersionDTO appVersionDTO);


    APPVersionDTO get(@Param("source") String source);


}
