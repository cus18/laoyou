/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhl.laoyou.common.service.TreeService;
import com.yhl.laoyou.modules.sys.dao.OfficeDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService
//		extends TreeService<OfficeDao, Office>
{

	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
//		office.setParentIds(office.getParentIds()+"%");
//		return dao.findByParentIdsLike(office);
		return null;
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
//		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
//		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}
