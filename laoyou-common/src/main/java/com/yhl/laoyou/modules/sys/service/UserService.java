/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.service;

import com.yhl.laoyou.common.constant.ConfigConstant;
import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.LoginDto;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysHospitalUserDTO;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysPractitionerUserDTO;
import com.yhl.laoyou.common.security.Digests;
import com.yhl.laoyou.common.service.BaseService;
import com.yhl.laoyou.common.utils.Encodes;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.sys.dao.*;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.utils.DaHanTricomSMSMessageUtil;
import com.yhl.laoyou.modules.sys.utils.LogUtils;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * 用户相关
 *
 * @author 张博
 * @version 2017-5-3
 */
@Service
@Transactional(readOnly = false)
public class UserService extends BaseService {

    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DaHanTricomMessageDao daHanTricomMessageDao;

    @Autowired
    private SysPractitionerUserDao sysPractitionerUserDao;

    @Autowired
    private EasemobGroupDao easemobGroupDao;

    @Autowired
    private SysElderUserDao sysElderUserDao;

    @Autowired
    private SysHospitalUserDao sysHospitalUserDao;

    @Autowired
    private EasemobService easemobService;

    public LoginDto login(String phone, String validateCode, String source, String loginIP, HttpServletRequest request) throws Exception {
        if (true) {
//        if (daHanTricomMessageDao.searchIdentify(phone, validateCode) > 0||(StringUtils.toInteger(phone)<100011&&validateCode.equals("1234"))) {
            User user = new User();
            user.setLoginName(phone);
            user = userDao.getByLoginName(user);
            if (user == null) {
                user = new User();
                user.setLoginName(phone);
                user.setId(UUIDUtil.getUUID());
                user.setPhone(phone);
                user.setCreateDate(new Date());
                user.setLoginIp(loginIP);
                userDao.insert(user);
                user.setSource(source);
                if (source.equals("hospital")) {
                    request.getSession().setAttribute("user", user.getId());
                }
                String easemobUserID = source + "_" + user.getId();
                String easemobPassword = UUIDUtil.getUUID();
                LoginDto loginDto = new LoginDto();
//                if (source.equals("practitioner")) {
//                    easemobService.signEasemobUser(easemobUserID, easemobPassword);
//                    SysPractitionerUserDTO sysPractitionerUser = new SysPractitionerUserDTO();
//                    sysPractitionerUser.setId(UUIDUtil.getUUID());
//                    sysPractitionerUser.setSysUserID(user.getId());
//                    sysPractitionerUser.setEasemobID(easemobUserID);
//                    sysPractitionerUser.setEasemobPassword(easemobPassword);
//                    sysPractitionerUserDao.insertSysPractitionerUser(sysPractitionerUser);
//                    user.setSysPractitionerUser(sysPractitionerUser);
//                    loginDto.setEasemobID(user.getSysPractitionerUser().getEasemobID());
//                    loginDto.setEasemobPassword(user.getSysPractitionerUser().getEasemobPassword());
//                }
                if (source.equals("elder")) {
                    easemobService.signEasemobUser(easemobUserID, easemobPassword);
                    SysElderUserDTO sysElderUserDTO = new SysElderUserDTO();
                    sysElderUserDTO.setId(UUIDUtil.getUUID());
                    sysElderUserDTO.setSysUserID(user.getId());
                    sysElderUserDTO.setEasemobPassword(easemobPassword);
                    sysElderUserDTO.setEasemobID(easemobUserID);
                    sysElderUserDao.insertSysElderUser(sysElderUserDTO);
                    user.setSysElderUserDTO(sysElderUserDTO);
                    loginDto.setEasemobID(user.getSysElderUserDTO().getEasemobID());
                    loginDto.setEasemobPassword(user.getSysElderUserDTO().getEasemobPassword());
                }
                String loginToken = UUIDUtil.getUUID() + source;
                JedisUtils.setObject(loginToken, user, ConfigConstant.loginTokenPeriod);
                LogUtils.saveLog(request, "新用户登录", user.getId() + "--" + source + "---" + loginIP);
                loginDto.setLoginToken(loginToken);
//                loginDto.setLoginToken("00000");
                return loginDto;
            } else {
                LoginDto loginDto = new LoginDto();
                if (source.equals("practitioner")) {
                    SysPractitionerUserDTO sysPractitionerUser = sysPractitionerUserDao.getSysPractitioner(user.getId());
                    user.setSysPractitionerUser(sysPractitionerUser);
                    if (sysPractitionerUser == null) {
                        loginDto.setLoginToken("00000");
                    } else {
                        loginDto.setLoginToken(UUIDUtil.getUUID() + source);
                    }
                } else if (source.equals("elder")) {
                    SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElder(user.getId());
                    user.setSysElderUserDTO(sysElderUserDTO);
                    if (sysElderUserDTO == null) {
                        loginDto.setLoginToken("00000");
                    } else {
                        loginDto.setLoginToken(UUIDUtil.getUUID() + source);
                    }
                } else if (source.equals("hospital")) {
                    SysHospitalUserDTO sysHospitalUserDTO = sysHospitalUserDao.getSysHospitalUserByUserID(user.getId());
                    user.setSysHospitalUserDTO(sysHospitalUserDTO);
                    if (sysHospitalUserDTO == null) {
                        SysPractitionerUserDTO sysPractitionerUser = sysPractitionerUserDao.getSysPractitioner(user.getId());
                        user.setSysPractitionerUser(sysPractitionerUser);
                        if (sysPractitionerUser == null) {
                            loginDto.setLoginToken("00000");
                        }else {
                            loginDto.setLoginToken(UUIDUtil.getUUID() + source);
                            HttpSession session=request.getSession();
                            session.setAttribute("token",loginDto.getLoginToken());
                            session.setMaxInactiveInterval(ConfigConstant.loginTokenPeriod);
                        }
                    } else {
                        loginDto.setLoginToken(UUIDUtil.getUUID() + source);
                        HttpSession session=request.getSession();
                        session.setAttribute("token",loginDto.getLoginToken());
                        session.setMaxInactiveInterval(ConfigConstant.loginTokenPeriod);
                    }
                }
                if (loginDto.getLoginToken() != null && !loginDto.getLoginToken().equals("00000")) {
                    if (source.equals("elder")) {
                        loginDto.setEasemobID(user.getSysElderUserDTO().getEasemobID());
                        loginDto.setEasemobPassword(user.getSysElderUserDTO().getEasemobPassword());
                        JedisUtils.del(user.getSysElderUserDTO().getLoginToken());
                        SysElderUserDTO sysElderUserDTO = new SysElderUserDTO();
                        sysElderUserDTO.setId(user.getSysElderUserDTO().getId());
                        sysElderUserDTO.setLoginToken(loginDto.getLoginToken());
                        sysElderUserDao.updateLoginToken(sysElderUserDTO);
                    } else if (source.equals("practitioner")) {
                        loginDto.setEasemobID(user.getSysPractitionerUser().getEasemobID());
                        loginDto.setEasemobPassword(user.getSysPractitionerUser().getEasemobPassword());
                        JedisUtils.del(user.getSysPractitionerUser().getLoginToken());
                        SysPractitionerUserDTO sysPractitionerUserDTO = new SysPractitionerUserDTO();
                        sysPractitionerUserDTO.setId(user.getSysPractitionerUser().getId());
                        sysPractitionerUserDTO.setLoginToken(loginDto.getLoginToken());
                        sysPractitionerUserDao.updateLoginToken(sysPractitionerUserDTO);
                    } else if (source.equals("hospital")) {
                        if(user.getSysHospitalUserDTO()!=null) {
                            JedisUtils.del(user.getSysHospitalUserDTO().getLoginToken());
                            SysHospitalUserDTO sysHospitalUserDTO = new SysHospitalUserDTO();
                            sysHospitalUserDTO.setId(user.getSysHospitalUserDTO().getId());
                            sysHospitalUserDTO.setLoginToken(loginDto.getLoginToken());
                            sysHospitalUserDao.updateLoginToken(sysHospitalUserDTO);
                        }else{
                            JedisUtils.del(user.getSysPractitionerUser().getLoginToken());
                            SysPractitionerUserDTO sysPractitionerUserDTO = new SysPractitionerUserDTO();
                            sysPractitionerUserDTO.setId(user.getSysPractitionerUser().getId());
                            sysPractitionerUserDTO.setLoginToken(loginDto.getLoginToken());
                            sysPractitionerUserDao.updateLoginToken(sysPractitionerUserDTO);
                        }
                    }
                    JedisUtils.setObject(loginDto.getLoginToken(), user, ConfigConstant.loginTokenPeriod);
                    LogUtils.saveLog(request, "用户登录", user.getId() + "--" + source + "---" + loginIP);
                }
                return loginDto;
            }
        } else {
            return null;
        }
    }

    public String loginOut(String loginToken) {
        JedisUtils.del(loginToken);
        return StatusConstant.LOGIN_OUT;
    }


    public String offline(String loginToken) {
        User loginUser = (User) JedisUtils.getObject(loginToken);
        LogUtils.saveLog("登录状态检测", loginToken);
        if (loginUser.getId() == null || loginUser.getId().equals("")) {
            return StatusConstant.OFFLINE;
        } else {
            return StatusConstant.ONLINE;
        }
    }


    public boolean insertUser(User user) {
        if (userDao.insert(user) > 1)
            return true;
        else
            return false;
    }

    public boolean updateUserInfo(User user) {
        if (userDao.update(user) > 1)
            return true;
        else
            return false;
    }

    public boolean updateUser(User user) {
        if (userDao.updateUser(user) > 0)
            return true;
        else
            return false;
    }

    /**
     * 获取当前登录的User
     *
     * @param request
     * @return
     */
    public static User getUser(HttpServletRequest request) {
        String loginToken = request.getHeader("logintoken");
        if(loginToken==null||loginToken.equals("")){
            try {
                loginToken=request.getSession().getAttribute("token").toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        User user = (User) JedisUtils.getObject(loginToken);
        return user;
    }


    /**
     * 获取当前登录的User
     *
     * @param request
     * @return
     */
    public User updateRedisUser(HttpServletRequest request) {
        String loginToken = request.getHeader("logintoken");
        if(loginToken==null||loginToken.equals("")){
            try {
                loginToken=request.getSession().getAttribute("token").toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        User user = (User) JedisUtils.getObject(loginToken);
        user = userDao.getByLoginName(user);
        if (loginToken.indexOf("hospital") > 0) {
            SysHospitalUserDTO sysHospitalUserDTO = sysHospitalUserDao.getSysHospitalUserByUserID(user.getId());
            user.setSysHospitalUserDTO(sysHospitalUserDTO);
        } else if (loginToken.indexOf("elder") > 0) {
            SysElderUserDTO sysElderUserDTO = sysElderUserDao.getSysElder(user.getId());
            user.setSysElderUserDTO(sysElderUserDTO);
        } else {
            SysPractitionerUserDTO sysPractitionerUser = sysPractitionerUserDao.getSysPractitioner(user.getId());
            user.setSysPractitionerUser(sysPractitionerUser);
        }
        JedisUtils.setObject(loginToken, user, ConfigConstant.loginTokenPeriod);
        return user;
    }


    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }


    public String getEasemobGroupIDByElderID(String elderID) {
        return easemobGroupDao.getEasemobGroupIDByElderID(elderID).getEasemobGroupID();
    }

    public String getEasemobUserId(String elderId) {
        return sysElderUserDao.getSysElderUser(elderId).getEasemobID();
    }


    public String sendMessage(String phoneNum) {
        try {
            String num = DaHanTricomSMSMessageUtil.sendIdentifying(phoneNum);
            daHanTricomMessageDao.insertIdentifying(phoneNum, num);
            return StatusConstant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusConstant.FAILURE;
        }
    }

    /**
     * 获取当前登录的User
     *
     * @param
     * @return
     */
    public  User getUserByLoginName(String loginName) {
        User user = new User();
        user.setLoginName(loginName);
        userDao.getByLoginName(user);
        return user;
    }

}
