/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.modules.myService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.activityService.entity.ActivityEasemobGroup;
import com.yhl.laoyou.modules.activityService.entity.ActivityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@MyBatisDao
@Repository
public interface ActivityEasemobGroupDao {

	Integer addActivityEasemobGroup(ActivityEasemobGroup activityEasemobGroup);

	Integer updateActivityEasemobGroup(ActivityEasemobGroup activityEasemobGroup);

	ActivityEasemobGroup searchActivityEasemobGroupByID(String id);

	List<ActivityEasemobGroup> getUserActivityEasemobGroupList(@Param("elderEasemobID") String elderEasemobID);


	ActivityEasemobGroup searchActivityEasemobGroupByGroupID(@Param("groupId")String groupId);


}
