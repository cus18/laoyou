package com.yhl.laoyou.modules.hospitalService.service.impl;

import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.hospitalService.dao.SysUserRoleDao;
import com.yhl.laoyou.modules.hospitalService.entity.SysUserRole;
import com.yhl.laoyou.modules.hospitalService.service.OfficeService;
import com.yhl.laoyou.modules.hospitalService.service.UserManageService;
import com.yhl.laoyou.modules.sys.dao.OfficeDao;
import com.yhl.laoyou.modules.sys.dao.SysHospitalUserDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by zbm84 on 2017/9/12.
 */
@Service
@Transactional(readOnly = false)
public class UserManageServiceImpl implements UserManageService{


    @Autowired
    private SysHospitalUserDao sysHospitalUserDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addUser(SysHospitalUserDTO sysHospitalUserDTO, HttpServletRequest request) {
        User user = new User();
        user.setLoginName(sysHospitalUserDTO.getPhone());
        user = userDao.getByLoginName(user);
        if (user == null) {
            user = new User();
            user.setLoginName(sysHospitalUserDTO.getPhone());
            user.setId(UUIDUtil.getUUID());
            user.setPhone(sysHospitalUserDTO.getPhone());
            user.setPhoto(sysHospitalUserDTO.getPhoto());
            user.setGender(sysHospitalUserDTO.getGender());
            user.setCreateDate(new Date());
            user.setName(sysHospitalUserDTO.getName());
//            user.setLoginIp(request.getRemoteAddr());
            userDao.insert(user);
        }
        if(sysHospitalUserDao.getSysHospitalUserByUserID(user.getId())!=null){
            return 9;
        }
        sysHospitalUserDTO.setId(UUIDUtil.getUUID());
        sysHospitalUserDTO.setSysUserID(user.getId());
        sysHospitalUserDao.insertSysHospitalUser(sysHospitalUserDTO);
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleID(sysHospitalUserDTO.getSysRoleID());
        sysUserRole.setSysHospitalUserID(sysHospitalUserDTO.getId());
        return sysUserRoleDao.addSysUserRole(sysUserRole);
    }

    @Override
    public Integer updateUser(SysHospitalUserDTO sysHospitalUserDTO) {
        User user=new User();
        user.setName(sysHospitalUserDTO.getName());
        user.setGender(sysHospitalUserDTO.getGender());
        user.setPhoto(sysHospitalUserDTO.getPhoto());
        user.setPhone(sysHospitalUserDTO.getPhone());
        user.setLoginName(sysHospitalUserDTO.getPhone());
        user.setId(sysHospitalUserDTO.getSysUserID());
        userDao.updateUser(user);
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleID(sysHospitalUserDTO.getSysRoleID());
        sysUserRole.setSysHospitalUserID(sysHospitalUserDTO.getId());
        return sysUserRoleDao.updateSysUserRole(sysUserRole);
    }

    @Override
    public Page getUserList(String searchValue, Page page) {
        return sysHospitalUserDao.getSysHospitalUserList(searchValue,page);
    }

    @Override
    public SysHospitalUserDTO getUserByID(String id) {
        return sysHospitalUserDao.getSysHospitalUserByUserID(id);
    }

    @Override
    public Integer deleteUser(String id) {
        User user=new User();
        user.setId(id);
        return userDao.delete(user);
    }
}
