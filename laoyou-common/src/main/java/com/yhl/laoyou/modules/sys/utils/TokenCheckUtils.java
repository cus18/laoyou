/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.utils;

import com.yhl.laoyou.common.constant.ConfigConstant;
import com.yhl.laoyou.common.utils.JedisUtils;
import com.yhl.laoyou.modules.sys.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class TokenCheckUtils {

	public static void checkTokenValidate(HttpServletRequest request) throws Exception {

		Map<String, String> tokenValue = getHeadersInfo(request);
		String token = tokenValue.get("loginToken");

		//验证token有效性
		int loginTokenPeriod = ConfigConstant.loginTokenPeriod;
		User user = (User) JedisUtils.getObject(token);
		if(user==null)
		{
			throw new Exception("loginTokenError");
		}
		JedisUtils.setObject(token, user, loginTokenPeriod);
	}

	//get request headers
	private static Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
}
