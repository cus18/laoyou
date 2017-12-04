package com.yhl.laoyou.modules.hospitalService.service;

import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.persistence.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zbm84 on 2017/9/18.
 */
public interface UserManageService {

    Integer addUser(SysHospitalUserDTO sysHospitalUserDTO, HttpServletRequest request);

    Integer updateUser(SysHospitalUserDTO sysHospitalUserDTO);

    Page getUserList(String searchValue,Page page);

    SysHospitalUserDTO getUserByID(String id);

    Integer deleteUser(String id);
}
