package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.constant.StatusConstant;
import com.yhl.laoyou.common.dto.BannerDTO;
import com.yhl.laoyou.common.dto.BannerResourceDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.persistence.Page;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	/**
	 * 添加 Banner 图
	 *
	 */
	@RequestMapping(value = "laoyou/addBanner", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<BannerDTO> addBanner(@RequestBody BannerDTO bannerDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		if (bannerService.addBanner(bannerDTO)>0){
			responseDto.setResult(StatusConstant.SUCCESS);
			responseDto.setErrorInfo("添加成功");
			return responseDto;
		}else {
			responseDto.setResult(StatusConstant.FAILURE);
			responseDto.setErrorInfo("添加失败");
			return responseDto;
		}
	}

	/**
	 * 获取 Banner 图
	 *
			 */
	@RequestMapping(value = "laoyou/getBanner", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO<List<BannerDTO>> getBanner(@RequestBody BannerDTO bannerDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		List<BannerDTO> list=bannerService.getBanner(bannerDTO);
		responseDto.setResponseData(list);
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 下架
	 */
	@RequestMapping(value = "laoyou/bannerTurnOff", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO bannerOff(@RequestBody BannerDTO bannerDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		bannerDTO.setStatus("1");
		responseDto.setResponseData(bannerService.bannerOff(bannerDTO));
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 资源位列表
	 */
	@RequestMapping(value = "laoyou/bannerResourceList", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO bannerResourceList(@RequestBody BannerResourceDTO bannerResourceDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		Page page=new Page();
		page.setPageSize(bannerResourceDTO.getPageSize());
		page.setPageNo(bannerResourceDTO.getPageNo());
		responseDto.setResponseData(bannerService.getBannerResourcePage(bannerResourceDTO,page));
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 编辑资源位列表
	 */
	@RequestMapping(value = "laoyou/updateSysBannerResource", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO updateSysBannerResource(@RequestBody BannerResourceDTO bannerResourceDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		responseDto.setResponseData(bannerService.updateSysBannerResource(bannerResourceDTO));
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 添加资源位列表
	 */
	@RequestMapping(value = "laoyou/addSysBannerResource", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO addSysBannerResource(@RequestBody BannerResourceDTO bannerResourceDTO, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		responseDto.setResponseData(bannerService.addSysBannerResource(bannerResourceDTO));
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 获取资源位
	 */
	@RequestMapping(value = "laoyou/getBannerType", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO getBannerType(@RequestParam String appName, HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		BannerResourceDTO bannerResourceDTO=new BannerResourceDTO();
		bannerResourceDTO.setType("B");
		bannerResourceDTO.setApp(appName);
		List<BannerResourceDTO> list=bannerService.getBannerResourcePage(bannerResourceDTO,new Page()).getList();
		List<BannerResourceDTO> result=new ArrayList<>();
		for (BannerResourceDTO b:list) {
			BannerResourceDTO ba=new BannerResourceDTO();
			ba.setType(ba.getType());
			ba.setNums(ba.getNums());
			result.add(ba);
		}
		responseDto.setResponseData(result);
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}

	/**
	 * 获取轮播位
	 */
	@RequestMapping(value = "laoyou/getBannerApps", method = {RequestMethod.POST, RequestMethod.GET})
	@LoginRequired
	public
	@ResponseBody
	ResponseDTO getBannerApps(HttpServletRequest request) {
		ResponseDTO responseDto=new ResponseDTO<>();
		BannerResourceDTO bannerResourceDTO=new BannerResourceDTO();
		bannerResourceDTO.setType("a");
		Page page=bannerService.getBannerResourcePage(bannerResourceDTO,new Page());
		List<BannerResourceDTO> list=page.getList();
		List<String> result=new ArrayList<>();
		for (BannerResourceDTO b:list) {
			result.add(b.getApp());
		}
		responseDto.setResponseData(result);
		responseDto.setResult(StatusConstant.SUCCESS);
		return responseDto;
	}
}
