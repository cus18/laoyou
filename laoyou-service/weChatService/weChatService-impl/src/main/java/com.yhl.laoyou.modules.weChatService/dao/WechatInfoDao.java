package com.yhl.laoyou.modules.weChatService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;

import java.util.HashMap;

/**
 * Created by sunxiao on 2017/9/11.
 */
@MyBatisDao
public interface WechatInfoDao {

    void updateWechatParameter(HashMap<String, Object> Map);

    HashMap<String,Object> getWechatParameter();

}
