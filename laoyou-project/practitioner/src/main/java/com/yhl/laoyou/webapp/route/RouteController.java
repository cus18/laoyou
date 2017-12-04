/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.webapp.route;

import com.yhl.laoyou.common.constant.ConfigConstant;
import com.yhl.laoyou.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        practitioner 项目
    */
    @RequestMapping(value ="",method = {RequestMethod.POST, RequestMethod.GET})
    public String practitioner(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "angular/practitionerIndex";
    }

    /*
        practitioner 项目
    */
    @RequestMapping(value ="native",method = {RequestMethod.POST, RequestMethod.GET})
    public String practitionerNative(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "native/practitionerFirstPage";
    }

    /*
    practitioner 项目
*/
    @RequestMapping(value ="test",method = {RequestMethod.POST, RequestMethod.GET})
    public String testNative(HttpServletResponse response) {
        response.addHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expires","0");
        return "test/first";
    }

}

