package com.yhl.laoyou.modules.sys.service;


import com.yhl.laoyou.common.dto.BannerResourceDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.sys.dao.SysBannerDao;
import com.yhl.laoyou.common.dto.BannerDTO;
import com.yhl.laoyou.modules.sys.dao.SysBannerResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = false)
public class BannerService {


    @Autowired
    private SysBannerDao sysBannerDao;

    @Autowired
    private SysBannerResourceDao sysBannerResourceDao;

    public Integer addBanner(BannerDTO bannerDTO) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (simpleDateFormat.format(date).equals(simpleDateFormat.format(bannerDTO.getStartDate()))) {
            List<BannerDTO> list = sysBannerDao.getBannerList(bannerDTO);
            if (list.size()>0) {
                BannerDTO bannerDTO1=list.get(0);
                bannerDTO1.setStatus("1");
                sysBannerDao.bannerOff(bannerDTO1);
            }
            bannerDTO.setStatus("0");
        }
        return sysBannerDao.addBanner(bannerDTO);
    }

    public List<BannerDTO> getBanner(BannerDTO bannerDTO) {
        return sysBannerDao.getBanner(bannerDTO);
    }

    public List<BannerDTO> getBannerList(BannerDTO bannerDTO) {
        return sysBannerDao.getBannerList(bannerDTO);
    }


    public Integer bannerOff(BannerDTO bannerDTO) {
        return sysBannerDao.bannerOff(bannerDTO);
    }

    public void timeUpdateBannerStatus() {
        BannerDTO bannerDTO1 = new BannerDTO();
        bannerDTO1.setEndDate(new Date());
        bannerDTO1.setStatus("0");
        List<BannerDTO> list = sysBannerDao.getBannerList(bannerDTO1);
        for (BannerDTO b : list) {
            b.setStatus("1");
            sysBannerDao.bannerOff(b);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BannerDTO bannerDTO = new BannerDTO();
        bannerDTO.setStartDate(new Date());
        bannerDTO.setStatus("1");
        List<BannerDTO> list1 = sysBannerDao.getBannerList(bannerDTO);
        for (BannerDTO banner : list1) {
            BannerDTO banner2 = new BannerDTO();
            banner2.setStatus("0");
            banner2.setType(banner.getType());
            banner2.setBannerResourceID(banner.getBannerResourceID());
            banner2.setWeights(banner.getWeights());
            List<BannerDTO> list3 = sysBannerDao.getBannerList(banner2);
            for (BannerDTO banner3 : list3) {
                banner3.setStatus("1");
                sysBannerDao.bannerOff(banner3);
            }
            banner.setStatus("0");
            sysBannerDao.bannerOff(banner);
        }
    }

    public Integer addSysBannerResource(BannerResourceDTO bannerResourceDTO){
        return sysBannerResourceDao.addSysBannerResource(bannerResourceDTO);
    }

    public Integer updateSysBannerResource(BannerResourceDTO bannerResourceDTO){
        return sysBannerResourceDao.updateSysBannerResource(bannerResourceDTO);
    }

    public Page getBannerResourcePage(BannerResourceDTO bannerResourceDTO,Page page){
        return sysBannerResourceDao.getBannerResourcePage(bannerResourceDTO,page);
    }



}
