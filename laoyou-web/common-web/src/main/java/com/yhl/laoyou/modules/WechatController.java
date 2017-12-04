package com.yhl.laoyou.modules;

import com.yhl.laoyou.common.utils.SignUtil;
import com.yhl.laoyou.modules.sys.annotation.LoginRequired;
import com.yhl.laoyou.modules.sys.entity.User;
import com.yhl.laoyou.modules.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.yhl.laoyou.modules.weChatService.service.WechatCoreService;

/**
 * Created by sunxiao on 2017/9/11.
 */
@Controller
@RequestMapping(value = "weChat")
public class WechatController {

//    @Autowired
//    WechatCoreService wechatCoreService;

    /**
     *用户校验是否是微信服务器发送的请求,处理微信事件
     */
    @RequestMapping(value = "/event", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    String wechatEvent(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod().toUpperCase();
        if ("GET".equals(method)) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            }
            return "";
        } else {
            // 调用核心业务类接收消息、处理消息
            String respMessage = null;
//            try {
//                respMessage = wechatCoreService.processWechatRequest(request,response);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return respMessage;
        }
    }

    /**
     * 根据信息生成二维码图
     * @param idCard
     * @param batch
     * @return
     */
    @RequestMapping(value = "/getUserQRCode", method = {RequestMethod.POST, RequestMethod.GET})
    @LoginRequired
    public
    @ResponseBody
    String getUserQRCode(@RequestParam(required = true) String idCard,
                         @RequestParam(required = true) String batch,
                         HttpServletRequest request) {
        User user = UserService.getUser(request);
        String info = batch + "-" + idCard + "-" + user.getMobile();
        String QRCodeUrl = null;//wechatCoreService.getUserQRCode(info);
        return QRCodeUrl;
    }
}
