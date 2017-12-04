/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.common.utils;

import com.yhl.laoyou.common.dto.practitioner.member.MemberDTO;
import com.yhl.laoyou.common.dto.PageParamDTO;

/**
 * Cache工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class CheckParam {

	public static void checkPageParamDto(PageParamDTO<MemberDTO> pageParamDto) throws Exception {
		if(pageParamDto == null)
		{
			throw new Exception("pageParamDto is null");
		}
	}

	
}
