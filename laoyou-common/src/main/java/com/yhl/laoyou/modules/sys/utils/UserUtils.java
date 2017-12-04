/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.utils;

import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yhl.laoyou.common.config.Global;
import com.yhl.laoyou.modules.sys.entity.Area;
import com.yhl.laoyou.modules.sys.entity.Menu;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.Role;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {
	
	private static String appName = Global.getConfig("web.app.name");
	private static boolean isBackend = "backend".equals(appName);
	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		return UserUtilsSpringSecurityImpl.get(id);
	}
	

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		UserUtilsSpringSecurityImpl.clearCache();
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){

	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		return UserUtilsSpringSecurityImpl.getUser();
	}

	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		if(isBackend)
			return UserUtilsShiroImpl.getAreaList();
		else
			return UserUtilsSpringSecurityImpl.getAreaList();
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		if(isBackend)
			return UserUtilsShiroImpl.getOfficeAllList();
		else
			return UserUtilsSpringSecurityImpl.getOfficeAllList();
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return UserUtilsShiroImpl.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		return UserUtilsShiroImpl.getPrincipal();
	}
	
	public static Session getSession(){
		return UserUtilsShiroImpl.getSession();
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		if(isBackend)
			return UserUtilsShiroImpl.getCache(key);
		else
			return UserUtilsSpringSecurityImpl.getCache(key);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		if(isBackend)
			return UserUtilsShiroImpl.getCache(key, defaultValue);
		else
			return UserUtilsSpringSecurityImpl.getCache(key, defaultValue);
	}

	public static void putCache(String key, Object value) {
		if(isBackend)
			UserUtilsShiroImpl.putCache(key, value);
		else
			UserUtilsSpringSecurityImpl.putCache(key, value);
	}

	public static void removeCache(String key) {
		if(isBackend)
			UserUtilsShiroImpl.removeCache(key);
		else
			UserUtilsSpringSecurityImpl.removeCache(key);
	}


	public static User getByLoginName(String loginName) {
		return null;
	}
}
