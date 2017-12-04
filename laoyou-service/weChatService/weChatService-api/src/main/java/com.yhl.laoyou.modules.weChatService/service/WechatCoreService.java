package com.yhl.laoyou.modules.weChatService.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunxiao on 2017/9/11.
 */
public interface WechatCoreService {

    String processWechatRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String getUserQRCode(String info);

    void updateWechatInfo();
}
