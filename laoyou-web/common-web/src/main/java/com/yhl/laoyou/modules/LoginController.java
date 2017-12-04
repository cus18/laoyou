/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.LoginDto;
import com.yhl.laoyou.common.dto.UserInfoDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.web.BaseController;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 登录 Controller
 *
 * @author 张博
 * @version 2017-5-5
 */
@Controller
public class LoginController extends BaseController {


    private static Lock lock = new ReentrantLock();

    @Autowired
    private UserService userService;


    /**
     * 登录实现
     */
    @RequestMapping(value = "laoyou/login", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseDTO<LoginDto> login(@RequestBody UserInfoDTO userInfoDto, HttpServletRequest request) throws Exception {
        LoginDto loginDto = userService.login(userInfoDto.getPhoneNum(), userInfoDto.getIdentifyNum(), userInfoDto.getSource(), request.getRemoteAddr().toString(), request);
        ResponseDTO<LoginDto> result = new ResponseDTO<LoginDto>();
        if (loginDto == null) {
            result.setResult(StatusConstant.FAILURE);
            result.setErrorInfo("验证码输入不正确");
            return result;
        } else if (loginDto.getLoginToken().equals("00000")) {
            result.setResult(StatusConstant.LOGIN_ERROR);
            result.setErrorInfo("用户不存在");
            result.setResponseData(loginDto);
            return result;
        } else {
            result.setResult(StatusConstant.SUCCESS);
            result.setErrorInfo("调用成功");
            result.setResponseData(loginDto);
            return result;
        }
    }

    /**
     * 登录实现
     */
    @RequestMapping(value = "laoyou/getUser", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<User> getUser(HttpServletRequest request) throws Exception {
        User user = userService.updateRedisUser(request);
        ResponseDTO<User> result = new ResponseDTO<User>();
        result.setResult(StatusConstant.SUCCESS);
        result.setErrorInfo("调用成功");
        result.setResponseData(user);
        return result;
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "laoyou/loginout", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<UserInfoDTO> loginout(HttpServletRequest request) {
        String loginToken=request.getHeader("logintoken");
        if(loginToken==null||loginToken.equals("")){
            loginToken=request.getSession().getAttribute("token").toString();
        }
        String status = userService.loginOut(loginToken);
        ResponseDTO<UserInfoDTO> result = new ResponseDTO<UserInfoDTO>();
        result.setResult(status);
        result.setErrorInfo(status.equals(StatusConstant.LOGIN_OUT) ? "退出登录" : "保持在线");
        return result;
    }

    /**
     * 发送心跳包检测登录状态
     */
    @RequestMapping(value = "laoyou/offline", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<UserInfoDTO> offline(HttpServletRequest request) {
        String status = userService.offline(request.getHeader("logintoken"));
        ResponseDTO<UserInfoDTO> result = new ResponseDTO<UserInfoDTO>();
        result.setResult(status);
        result.setErrorInfo(status.equals(StatusConstant.OFFLINE) ? "强制下线" : "保持在线");
        return result;
    }

    /**
     * 发送验证码
     */
    @RequestMapping(value = "laoyou/sendIdentifying", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseDTO<UserInfoDTO> sendIdentifying(@RequestBody UserInfoDTO userInfoDto) {
        ResponseDTO<UserInfoDTO> result = new ResponseDTO<UserInfoDTO>();
        result.setResult(userService.sendMessage(userInfoDto.getPhoneNum()));
        return result;
    }
}
