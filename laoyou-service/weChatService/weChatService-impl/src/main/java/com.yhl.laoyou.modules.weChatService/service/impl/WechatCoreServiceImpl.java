package com.yhl.laoyou.modules.weChatService.service.impl;

import com.yhl.laoyou.common.utils.*;
import com.yhl.laoyou.modules.myService.dao.SurveyDao;
import com.yhl.laoyou.modules.sys.entity.*;
import com.yhl.laoyou.modules.sys.utils.LogUtils;
import com.yhl.laoyou.modules.weChatService.dao.WechatAttentionDao;
import com.yhl.laoyou.modules.weChatService.dao.WechatInfoDao;
import com.yhl.laoyou.modules.weChatService.dao.WechatUserBindDao;
import com.yhl.laoyou.modules.weChatService.entity.WechatAttention;
import com.yhl.laoyou.modules.weChatService.service.WechatCoreService;
import com.yhl.laoyou.modules.weChatService.service.util.MessageUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by sunxiao on 2017/9/11.
 */
@Service
@Transactional(readOnly = false)
public class WechatCoreServiceImpl implements WechatCoreService{

    @Autowired
    WechatInfoDao wechatInfoDao;

    @Autowired
    WechatAttentionDao wechatAttentionDao;

    @Autowired
    WechatUserBindDao wechatUserBindDao;

    @Autowired
    SurveyDao surveyDao;

    @Override
    public String processWechatRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("processWechatRequest===================================");
        String respMessage = null;

        /** 解析xml数据 */
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(getXmlDataFromWechat(request));
        String msgType = xmlEntity.getMsgType();

        // xml请求解析
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            // 事件类型
            String eventType = xmlEntity.getEvent();
            if(eventType.equals(MessageUtil.SCAN)){
                //已关注公众号的情况下扫描
                //this.updateAttentionInfo(xmlEntity);
                respMessage = processScanEvent(xmlEntity,"oldUser",request,response);
            }else if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                //扫描关注公众号或者搜索关注公众号都在其中
                respMessage = processSubscribeEvent(xmlEntity, request,response);
            }
            // 取消订阅
            else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
                processUnSubscribeEvent(xmlEntity, request);
            }
            // 自定义菜单点击事件
            else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
                respMessage = processClickMenuEvent(xmlEntity,request,response);
            }
        }

        return respMessage;
    }

    private String getXmlDataFromWechat(HttpServletRequest request)
    {
        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        try {
            is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void updateAttentionInfo(ReceiveXmlEntity xmlEntity)
    {
        String EventKey = xmlEntity.getEventKey();
        Date updateDate = new Date();
        String openId = xmlEntity.getFromUserName();
        String marketer = EventKey.replace("qrscene_", "");
        HashMap<String,Object> updateTimeMap = new HashMap<String, Object>();
        updateTimeMap.put("openId",openId);
        updateTimeMap.put("updateTime", updateDate);
        updateTimeMap.put("doctorMarketer", marketer);
        wechatAttentionDao.updateAttentionInfo(updateTimeMap);
    }

    private String processSubscribeEvent(ReceiveXmlEntity xmlEntity,HttpServletRequest request,HttpServletResponse response)
    {
        Map parameter = wechatInfoDao.getWechatParameter();
        String token = (String) parameter.get("token");
        String EventKey = xmlEntity.getEventKey();
        String marketer = "";
        if(StringUtils.isNotNull(EventKey)){
            marketer = EventKey.replace("qrscene_", "");
        }
        this.insertAttentionInfo(xmlEntity, token, marketer);
        return sendSubScribeMessage(xmlEntity, request,response, marketer, token);
    }

    private void insertAttentionInfo(ReceiveXmlEntity xmlEntity,String token,String marketer)
    {
        HashMap<String,Object> map = new HashMap<String, Object>();
        String openId = xmlEntity.getFromUserName();
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        WechatBean wechatBean = WechatUtil.getWechatName(token, openId);

        map.put("id", id);
        map.put("status", "0");
        map.put("openId", openId);
        map.put("marketer", marketer);
        map.put("updateTime", new Date());
        map.put("nickname", EmojiFilter.coverEmoji(wechatBean.getNickname()));
        /*int attentionNum = wechatInfoDao.checkAttention(map);
        if(attentionNum > 0) {
            map.put("ispay",0);
        }
        else {
            map.put("ispay",1);
        }*/
        wechatAttentionDao.insertAttentionInfo(map);

    }

    private String sendSubScribeMessage(ReceiveXmlEntity xmlEntity,HttpServletRequest request,HttpServletResponse response,String marketer,String token)
    {
        HttpSession session = request.getSession();
        session.setAttribute("openId", xmlEntity.getFromUserName());
        LogUtils.saveLog(request, "00000001");//注：参数含义请参照sys_log_mapping表，如00000001表示“微信宝大夫用户版公众平台关注”
        String EventKey = xmlEntity.getEventKey();
        //if(EventKey.indexOf("xuanjianghuodong_zhengyuqiao_saoma")<=-1&&EventKey.indexOf("baoxian_000001")<=-1){
            List<Article> articleList = new ArrayList<Article>();
            Article article = new Article();
            article.setTitle("来啦，您请坐！\n");
            article.setDescription("我们是华录老友，在这里，将会为您实时传递最好的养老服务。");
            article.setPicUrl("");
            article.setUrl("");
            articleList.add(article);

            WechatUtil.senImgMsgToWechat(token,xmlEntity.getFromUserName(),articleList);

        //}


        return processScanEvent(xmlEntity,"newUser",request,response);
    }

    private void processUnSubscribeEvent(ReceiveXmlEntity xmlEntity,HttpServletRequest request)
    {
        // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
        HttpSession session = request.getSession();
        session.setAttribute("openId", xmlEntity.getFromUserName());
        LogUtils.saveLog(request, "00000002");//注：参数含义请参照sys_log_mapping表，如00000002表示取消关注”
        HashMap<String,Object> map = new HashMap<String, Object>();
        String openId = xmlEntity.getFromUserName();
        map.put("openId", openId);
        //根据openid查询最近关注的marketer，防止取消关注的时候marketer总是为空
        WechatAttention wechatAttention = new WechatAttention();
        wechatAttention.setOpenid(openId);
        wechatAttention = wechatAttentionDao.findMarketerByOpeinid(wechatAttention);
        map.put("marketer", wechatAttention.getMarketer());
        map.put("status", "1");
        map.put("updateTime", new Date());
        wechatAttentionDao.insertAttentionInfo(map);

    }

    private String processClickMenuEvent(ReceiveXmlEntity xmlEntity,HttpServletRequest request,HttpServletResponse response)
    {
        // TODO 自定义菜单
        String respMessage = "";
        if("39".equals(xmlEntity.getEventKey())){
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(xmlEntity.getFromUserName());
            textMessage.setFromUserName(xmlEntity.getToUserName());
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            String st = "";
            textMessage.setContent(st);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        else if("38".equals(xmlEntity.getEventKey()))
        {
            HttpSession session = request.getSession();
            session.setAttribute("openId",xmlEntity.getFromUserName());
            LogUtils.saveLog(request,"00000003");//注：参数含义请参照sys_log_mapping表，如00000003表示“咨询医生消息推送”
            Map parameter = wechatInfoDao.getWechatParameter();
            String token = (String) parameter.get("token");
            List<Article> articleList = new ArrayList<Article>();
            Article article = new Article();
            article.setTitle("");
            article.setDescription("");
            article.setPicUrl(" ");
            article.setUrl("https://mp.weixin.qq.com/s?__biz=MzI2MDAxOTY3OQ==&mid=504236660&idx=1&sn=10d923526047a5276dd9452b7ed1e302&scene=1&srcid=0612OCo7d5ASBoGRr2TDgjfR&key=f5c31ae61525f82ed83c573369e70b8f9b853c238066190fb5eb7b8640946e0a090bbdb47e79b6d2e57b615c44bd82c5&ascene=0&uin=MzM2NjEyMzM1&devicetype=iMac+MacBookPro11%2C4+OSX+OSX+10.11.4+build(15E65)&version=11020201&pass_ticket=dG5W6eOP3JU1%2Fo3JXw19SFBAh1DgpSlQrAXTyirZuj970HMU7TYojM4D%2B2LdJI9n");
            articleList.add(article);
            WechatUtil.senImgMsgToWechat(token,xmlEntity.getFromUserName(),articleList);
        }else if("36".equals(xmlEntity.getEventKey()))
        {
            List<Article> articleList = new ArrayList<Article>();
            // 创建图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(xmlEntity.getFromUserName());
            newsMessage.setFromUserName(xmlEntity.getToUserName());
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            Article article = new Article();
            article.setTitle("");
            article.setDescription("");
            article.setPicUrl("");
            article.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0MjAwNjY0Nw==&mid=208340985&idx=1&sn=2beb0d78fc9097f10d073e182f1cb6c1&scene=0#rd");
            articleList.add(article);
            // 设置图文消息个数
            newsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            newsMessage.setArticles(articleList);
            // 将图文消息对象转换成xml字符串
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        }

        return respMessage;
    }

    private String processScanEvent(ReceiveXmlEntity xmlEntity,String userType,HttpServletRequest request,HttpServletResponse response)
    {
        String EventKey = xmlEntity.getEventKey();
        System.out.println(EventKey + "EventKey=========================================");
        Article article = new Article();
        List<Article> articleList = new ArrayList<Article>();
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(xmlEntity.getFromUserName());
        newsMessage.setFromUserName(xmlEntity.getToUserName());
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        if(EventKey.indexOf("baoxian_000001")>-1&&xmlEntity.getEvent().equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(xmlEntity.getFromUserName());
            textMessage.setFromUserName(xmlEntity.getToUserName());
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            textMessage.setContent("尊敬");
            return MessageUtil.textMessageToXml(textMessage);

        }else if(EventKey.indexOf("FTQWJ")>-1){//丰台区问卷
            article.setTitle("感谢您参与我国老人摸底调查");
            int answerUserNum = surveyDao.getAnswerUserNum();
            article.setDescription("敬爱的老年朋友，非常感谢您积极配合政府进行问卷调查。参与本次调研的老年朋友已有" + answerUserNum + "人。\n" +
                    "本次调研由北京市丰台区民政局发起，华录老友受委托完成。华录老友会持续为您带来优质体验和服务。");
            article.setPicUrl("");
            article.setUrl("");
            articleList.add(article);
            /*String[] info = EventKey.split("-");
            WechatUserBind wechatUserBind = new WechatUserBind();
            wechatUserBind.setBatch(info[0]);
            wechatUserBind.setIdCard(info[1]);
            wechatUserBind.setPhone(info[2]);
            wechatUserBind.setOpenId(xmlEntity.getFromUserName());

            List<WechatUserBind> list = wechatUserBindDao.findWechatUserBindByInfo(wechatUserBind);
            if(list==null || list.size()==0){
                wechatUserBindDao.insertWechatUserBind(wechatUserBind);
            }*/
        }

        if(articleList.size() == 0){
            return "";
        }
        // 设置图文消息个数
        newsMessage.setArticleCount(articleList.size());
        // 设置图文消息包含的图文集合
        newsMessage.setArticles(articleList);
        // 将图文消息对象转换成xml字符串
        String respMessage = MessageUtil.newsMessageToXml(newsMessage);
        return respMessage;
    }

    /**
     * 根据信息生成二维码
     * @param info
     * @return
     */
    @Override
    public String getUserQRCode(String info) {
        Map<String,Object> parameter = wechatInfoDao.getWechatParameter();
        String token = (String)parameter.get("token");
        String url= "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
        String jsonData="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\",\"action_info\": {\"scene\": {\"scene_str\"" + ":\"" + info + "\"}}}";
        String reJson=WechatUtil.post(url, jsonData,"POST");
        System.out.println(reJson);
        JSONObject jb=JSONObject.fromObject(reJson);
        String qrTicket=jb.getString("ticket");
        String QRCodeURI="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+qrTicket;
        return QRCodeURI;
    }

    @Autowired
    public void updateWechatInfo(){
        try {
            System.out.print("用户端微信参数更新");
            String token = WechatUtil.getToken(ConstantUtil.CORPID, ConstantUtil.SECTET);
            if(StringUtils.isNotNull(token)) {
                String ticket = WechatUtil.getJsapiTicket(token);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("token", token);
                map.put("ticket", ticket);
                map.put("id", "1");

                wechatInfoDao.updateWechatParameter(map);
                //sessionRedisCache.putWeChatParamToRedis(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
