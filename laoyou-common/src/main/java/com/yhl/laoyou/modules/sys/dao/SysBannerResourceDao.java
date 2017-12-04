/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.dto.BannerDTO;
import com.yhl.laoyou.common.dto.BannerResourceDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Banner
 * @author 张博
 * @version 2017年8月3日
 */
@MyBatisDao
@Repository
public interface SysBannerResourceDao {

    Integer addSysBannerResource(BannerResourceDTO bannerResourceDTO);

    Integer updateSysBannerResource(BannerResourceDTO bannerResourceDTO);

    Page getBannerResourcePage(BannerResourceDTO bannerResourceDTO,Page page);
}
