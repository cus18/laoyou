/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.*;
import com.yhl.laoyou.common.dto.elder.ElderInfoDTO;
import com.yhl.laoyou.common.utils.OSSObjectTool;
import com.yhl.laoyou.common.web.BaseController;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.Menu;
import com.yhl.laoyou.modules.sys.service.APPVersionService;
import com.yhl.laoyou.modules.sys.service.MenuService;
import com.yhl.laoyou.modules.sys.service.UserService;
import com.yhl.laoyou.modules.sys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 工具 Controller
 *
 * @author ThinkGem
 * @version 2013-10-17
 */
@Controller
@RequestMapping(value = "util")
public class UtilController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private APPVersionService  appVersionService;

    /**
     * 上传文件
     * @param file
     * @param
     * @return {"status","success"}
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/uploadMediaFile",method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public ResponseDTO UploadFile(@RequestParam("file") MultipartFile file,String fileName) throws UnsupportedEncodingException {
        ResponseDTO response = new ResponseDTO();
        String path=fileName;
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
            file.transferTo(newFile);
            OSSObjectTool.uploadFileInputStream(path,newFile.length(),new FileInputStream(newFile),"yhllaoyou");
            response.setErrorInfo("上传成功");
            response.setResult(StatusConstant.SUCCESS);
            response.setResult(path);
        } catch (IOException e) {
            e.printStackTrace();
            response.setErrorInfo("上传失败");
            response.setResult(StatusConstant.FAILURE);
        }
        return response;
    }

    /**
     * 获取应用的菜单信息
     *  input menuType,获取菜单的类型
     *
     *  output HashMap<String,Object></>
     *	key为菜单名称
     *  value为菜单的H5　url
     */
    @LoginRequired
    @RequestMapping(value="/menu/getMenu",method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ResponseDTO<MenuParameterDTO> menuData(@RequestParam(required = true) String menuType) {
        MenuParameterDTO menuParameterDTO = menuService.getMenu(menuType);
        ResponseDTO<MenuParameterDTO> responseDto = new ResponseDTO<>();
        responseDto.setResponseData(menuParameterDTO);
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }

    @LoginRequired
    @RequestMapping(value="/menu/insertMenu",method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ResponseDTO insertMenu(@RequestBody MenuParameterDTO menuParameterDTO) {
        ResponseDTO responseDto=new ResponseDTO<>();
        Integer res = menuService.insertMenu(menuParameterDTO);
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }


    @RequestMapping(value="/version/getAPPVersion",method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ResponseDTO getAPPVersion(@RequestParam String source) {
        ResponseDTO responseDto=new ResponseDTO<>();
        responseDto.setResponseData(appVersionService.get(source));
        responseDto.setResult(StatusConstant.SUCCESS);
        return responseDto;
    }

}
