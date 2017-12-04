package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.elder.RelativeElderDTO;
import com.yhl.laoyou.common.dto.practitioner.healthService.DietPlanDTO;
import com.yhl.laoyou.modules.healthService.service.DietPlanService;
import com.yhl.laoyou.modules.healthService.service.HealthArchive;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 亲友圈
 *
 * @author 张博
 * @date 2017年5月25日
 */
@Controller
@RequestMapping(value = "health/")
public class RelativeELderController {

    @Autowired
    HealthArchive healthArchive;

    /**
     * 获取用户所有的亲友圈中亲友的信息
     * @return
     */
    @RequestMapping(value = "relativeElderInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    ResponseDTO<List<RelativeElderDTO>> relativeElderInfo(HttpServletRequest request) {

        ResponseDTO<List<RelativeElderDTO>> responseDTO = new ResponseDTO<>();


        //获取用户所有的亲友圈中亲友的信息，将用户的亲友圈的亲友信息放入RelativeElderDTO中
        responseDTO.setResponseData(healthArchive.getRelativeList(UserService.getUser(request)));
        responseDTO.setResult(StatusConstant.SUCCESS);
        return responseDTO;
    }


}
