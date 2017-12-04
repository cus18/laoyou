/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.sys.dao;

import java.util.List;
import com.yhl.laoyou.common.dto.practitioner.Settings.SysElderUserDTO;
import org.springframework.stereotype.Repository;
import com.yhl.laoyou.common.persistence.CrudDao;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
@Repository
public interface UserDao extends CrudDao<User> {

	User getByLoginName(User user);

	long findAllCount(User user);

	int updatePasswordById(User user);

	int updateLoginInfo(User user);

	int deleteUserRole(User user);

	int updateUserInfo(User user);

	int updateUser(User user);

	List<User> getUserByInfo(SysElderUserDTO dto);
}
