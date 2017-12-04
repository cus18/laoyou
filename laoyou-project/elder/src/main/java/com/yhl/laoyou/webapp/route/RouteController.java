/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.webapp.route;

import com.yhl.laoyou.common.constant.ConfigConstant;
import com.yhl.laoyou.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具 Controller
 *
 * @author ThinkGem
 * @version 2013-10-17
 */
@Controller
public class RouteController extends BaseController {


    /*
        elder 项目
    */
    @RequestMapping(value ="",method = {RequestMethod.POST, RequestMethod.GET})
    public String elder(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        //return "angular/elderIndex";
        return "angular/elderIonicIndex";
    }

    /*
        elder 项目
    */
    @RequestMapping(value ="native",method = {RequestMethod.POST, RequestMethod.GET})
    public String elderNative(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "native/elderFirstPage";
    }

    /*
        elder ionic框架
    */
    @RequestMapping(value ="ionic",method = {RequestMethod.POST, RequestMethod.GET})
    public String elderIonic(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "angular/elderIonicIndex";
    }

    /*
     elder ionic框架
    */
    @RequestMapping(value ="login",method = {RequestMethod.POST, RequestMethod.GET})
    public String elderLogin(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "native/login";
    }

    /*
     elder ionic框架
    */
    @RequestMapping(value ="surveyLogin",method = {RequestMethod.POST, RequestMethod.GET})
    public String surveyLogin(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "native/surveyLogin";
    }

    /*
 elder ionic框架
*/
    @RequestMapping(value ="aboutUs",method = {RequestMethod.POST, RequestMethod.GET})
    public String aboutUs(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "native/weChatCompany";
    }

}

