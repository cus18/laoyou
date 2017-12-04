package com.yhl.laoyou.modules.myService.dao;

import com.yhl.laoyou.common.dto.activity.ActivityDiscussDTO;
import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.activityService.entity.ActivityDiscuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zbm84 on 2017/7/27.
 */
@MyBatisDao
@Repository
public interface ActivityDiscussDao {

    Integer addActivityDiscuss(ActivityDiscuss activityDiscuss);

    List<ActivityDiscussDTO> getActivityDiscussList(@Param("id")String id,@Param("page") Integer page);

}
