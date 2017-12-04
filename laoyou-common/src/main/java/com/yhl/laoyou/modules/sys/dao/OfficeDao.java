/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.TreeDao;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import org.apache.ibatis.annotations.Param;


@MyBatisDao
public interface OfficeDao {

    int insertOffice(Office sysHospitalUserDTO);

    Office getOfficeByID(String ID);

    Page getOfficeList(@Param("searchValue")String searchValue, Page page);

    Integer updateOffice(Office office);

    Integer deleteOffice(String ID);
	
}
