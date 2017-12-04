package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.OperationType;
import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.dto.practitioner.healthServicePackage.HealthServicePackageTemplateDTO;
import com.yhl.laoyou.modules.healthDataService.service.ProductService;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxiao on 2017/6/23.
 */
@Controller
@RequestMapping(value = "product")
public class ProductServiceController {

    @Autowired
    ProductService productService;

    /**
     * 获取服务套餐的列表
     * @param dto
     * @return
     */
    @RequestMapping(value="/findProductService",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO findProductService(@RequestBody HealthServicePackageTemplateDTO dto) {
        ResponseDTO response = new ResponseDTO();
        List<HealthServicePackageTemplateDTO> list = new ArrayList<HealthServicePackageTemplateDTO>();
        productService.findProductService(list,dto);
        response.setResponseData(StatusConstant.SUCCESS);
        response.setResponseData(list);
        return response;
    }

    /**
     * 删除服务套餐
     * @param dto
     * @return
     */
    @RequestMapping(value="/delProductService",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO delProductService(@RequestBody HealthServicePackageTemplateDTO dto) {
        ResponseDTO response = new ResponseDTO();
        try {
            productService.operProductService(dto, OperationType.DELETE.getValue());
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.FAILURE);
            response.setErrorInfo("该模板绑定有用户，无法删除！");
        }
        return response;
    }

    /**
     * 更新服务套餐
     * @param dto
     * @return
     */
    @RequestMapping(value="/updateProductService",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO updateProductService(@RequestBody HealthServicePackageTemplateDTO dto) {
        ResponseDTO response = new ResponseDTO();
        try {
            productService.operProductService(dto,OperationType.UPDATE.getValue());
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.FAILURE);
            response.setErrorInfo("该模板绑定有用户，无法修改！");
        }
        return response;
    }

    /**
     * 添加服务套餐
     * @param dto
     * @return
     */
    @RequestMapping(value="/addProductService",method = {RequestMethod.POST, RequestMethod.GET})
    public
    @LoginRequired
    @ResponseBody
    ResponseDTO addProductService(@RequestBody HealthServicePackageTemplateDTO dto) {
        ResponseDTO response = new ResponseDTO();
        try {
            productService.operProductService(dto,OperationType.ADD.getValue());
            response.setResult(StatusConstant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(StatusConstant.FAILURE);
            response.setErrorInfo("添加失败！");
        }
        return response;
    }

}
