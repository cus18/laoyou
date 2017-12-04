/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.common.dto.BannerDTO;
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
public interface SysBannerDao {

    Integer addBanner(BannerDTO bannerDTO);

    List<BannerDTO> getBanner(BannerDTO bannerDTO);

    Integer bannerOff(BannerDTO bannerDTO);

    List<BannerDTO> getBannerList(BannerDTO bannerDTO);

    public void TimeUpdateBannerStatus();
}
