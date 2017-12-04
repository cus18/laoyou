package com.yhl.laoyou.modules.hospitalService.service.impl;

import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.hospitalService.service.OfficeService;
import com.yhl.laoyou.modules.sys.dao.OfficeDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zbm84 on 2017/9/12.
 */
@Service
@Transactional(readOnly = false)
public class OfficeServiceImpl implements OfficeService{

    @Autowired
    OfficeDao officeDao;

    @Override
    public Integer insertOffice(Office office) {
        return officeDao.insertOffice(office);
    }

    @Override
    public Page getOfficeList(String searchValue, Page page) {
        return officeDao.getOfficeList(searchValue,page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateOffice(Office office) {
        return officeDao.updateOffice(office);
    }

    @Override
    public Integer deleteOffice(Office office) {
        return officeDao.deleteOffice(office.getId());
    }

    @Override
    public Office getOfficeByID(String ID) {
        return officeDao.getOfficeByID(ID);
    }
}
