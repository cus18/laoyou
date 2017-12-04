package com.yhl.laoyou.modules.weChatService.dao;

import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.weChatService.entity.WechatUserBind;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunxiao on 2017/9/12.
 */
@MyBatisDao
public interface WechatUserBindDao {

    void insertWechatUserBind(WechatUserBind wechatUserBind);

    List<WechatUserBind> findWechatUserBindByInfo(WechatUserBind wechatUserBind);
}
