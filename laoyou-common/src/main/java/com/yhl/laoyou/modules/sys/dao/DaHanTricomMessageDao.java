/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.persistence.CrudDao;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.sys.entity.Role;
import com.yhl.laoyou.modules.sys.utils.DaHanTricomMessageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 信息DAO接口
 */
@MyBatisDao
@Repository
public interface DaHanTricomMessageDao {


	//插入验证码信息
	public int insertIdentifying(@Param(value="PhonNum") String PhonNum, @Param(value="Identifying") String Identifying);

	//查询验证码是否正确
	public int searchIdentify(@Param(value="PhonNum") String PhonNum, @Param(value="Identifying") String Identifying);
	

}
