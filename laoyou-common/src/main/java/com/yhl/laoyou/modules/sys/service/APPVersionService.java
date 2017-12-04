/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.service;

import com.yhl.laoyou.common.dto.APPVersionDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.service.CrudService;
import com.yhl.laoyou.common.utils.DateUtils;
import com.yhl.laoyou.modules.sys.dao.APPVersionDao;
import com.yhl.laoyou.modules.sys.dao.LogDao;
import com.yhl.laoyou.modules.sys.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class APPVersionService{

	@Autowired
	private APPVersionDao appVersionDao;

	public APPVersionDTO get(String source){
		return  appVersionDao.get(source);
	}

	public Integer insert(APPVersionDTO appVersionDTO){
		try {
			appVersionDao.insert(appVersionDTO);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return  0;
		}
	}
	
}
