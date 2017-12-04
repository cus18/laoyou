package com.yhl.laoyou.modules.weChatService.dao;


import com.yhl.laoyou.common.persistence.annotation.MyBatisDao;
import com.yhl.laoyou.modules.weChatService.entity.WechatAttention;

import java.util.HashMap;
import java.util.Map;

@MyBatisDao
public interface WechatAttentionDao {

    void insertAttentionInfo(HashMap<String,Object> codeMap);

    WechatAttention getAttentionByOpenId(String open_id);

    //根据openid查询最近关注的marketer，防止取消关注的时候marketer总是为空
    WechatAttention findMarketerByOpeinid(WechatAttention wechatAttention);

    void updateAttentionInfo(Map map);

}