/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.myService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.activityService.entity.ActivityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@MyBatisDao
@Repository
public interface ActivityUserDao {

	Integer addActivityUser(ActivityUser activityUser);

	/**
	 * 通过活动 ID 获取参与活动总人数
	 * @param activityID
	 * @return
	 */
	Integer getActivityCountByID(@Param("activityID") String activityID, @Param("elderID") String elderID);

	List<ActivityUser> getActivityUserList(@Param("activityID") String activityID);

}
