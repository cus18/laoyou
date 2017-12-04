package com.yhl.laoyou.modules.sys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yhl.laoyou.common.utils.CacheUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yhl.laoyou.common.service.BaseService;
import com.yhl.laoyou.common.utils.SpringContextHolder;
import com.yhl.laoyou.modules.sys.dao.AreaDao;
import com.yhl.laoyou.modules.sys.dao.MenuDao;
import com.yhl.laoyou.modules.sys.dao.OfficeDao;
import com.yhl.laoyou.modules.sys.dao.RoleDao;
import com.yhl.laoyou.modules.sys.dao.UserDao;
import com.yhl.laoyou.modules.sys.entity.Area;
import com.yhl.laoyou.modules.sys.entity.Menu;
import com.yhl.laoyou.modules.sys.entity.Office;
import com.yhl.laoyou.modules.sys.entity.Role;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.SystemService;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtilsSpringSecurityImpl {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";

	public static final String CACHE_USER = "user";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	
	
	public static User get(String id){
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
		}
		return user;
	}

    public static String getUserId(){
    	User u = getUser();
    	return u==null ? null:u.getId();
    }

    public static User getUser() {
        User user = (User) getCache(CACHE_USER);
        if (user == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                String loginName = null;
                if (principal instanceof org.springframework.security.core.userdetails.User) {
                    loginName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
                } else if (principal instanceof String) {
                    loginName = principal.toString();
                }
                if (loginName != null) {
                    user = systemService.getUserByLoginName(loginName);
                    putCache(CACHE_USER, user);
                }
            }
        }
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static User getUser(String userId) {
        return systemService.getUser(userId);
    }

    public static String getUserName(String userId) {
        User user = systemService.getUser(userId);
        if (user == null || user.getName() == null) {
            return "";
        } else {
            return user.getName();
        }
    }

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
    	RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    	if(attributes != null) {
	    	HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest(); 
	    	if(request != null) {
	    		HttpSession session = request.getSession();
	    		if(session != null)
	    			return session.getAttribute(key);
	    	}
    	}
    	return defaultValue;
    }

    public static void putCache(String key, Object value) {
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
    	if(request != null) {
    		HttpSession session = request.getSession();
    		if(session != null)
    			session.setAttribute(key, value);
    	}
    }

    public static void removeCache(String key) {
        getCacheMap().remove(key);
    }

    public static Map<String, Object> getCacheMap() {
        SecurityUtils.Subject subject = SecurityUtils.getSubject();
        SecurityUtils.Principal principal = subject.getPrincipal();
        return principal != null ? principal.getCacheMap() : new HashMap<String, Object>();
    }

    /**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		removeCache(CACHE_USER);
	}

	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}


	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
//			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
}
