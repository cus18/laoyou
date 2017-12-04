package com.yhl.laoyou.modules.sys.utils;

import com.alibaba.fastjson.JSON;
//import com.dahantc.api.sms.json.JSONHttpClient;
import com.yhl.laoyou.modules.sys.dao.DaHanTricomMessageDao;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 短信发送接口
 * Created by 张博 on 2017年5月3日
 */

public class DaHanTricomSMSMessageUtil {


    @Autowired
    private  DaHanTricomMessageDao daHanTricomMessageDao;

    private static String account = "dh35461";// 用户名（必填）
    private static String password = "H6x7j57V";// 密码（必填）
    public static String sign = "【老友】"; // 短信签名（必填）
    public static String subcode = ""; // 子号码（可选）

    /**
     * 发送短信接口,根据开关与否进行短信发送
     * @param phoneNum 发送短信手机号码
     * @param content  发送短息内容
     * @return 发送短信的结果
     * */
    public static boolean sendMsg(String phoneNum, String content) {
        try {
            String msgid = UUIDUtil.getUUID(32).toString(); // 短信id，查询短信状态报告时需要，（可选）
            //JSONHttpClient jsonHttpClient = new JSONHttpClient("http://www.dh3t.com");
            //jsonHttpClient.setRetryCount(1);
            String sendhRes = null;//jsonHttpClient.sendSms(account, password, phoneNum, content, sign, subcode,msgid);
            DaHanTricomMessageBean dtmb=JSON.parseObject(sendhRes,DaHanTricomMessageBean.class);
            if(dtmb.getResult().equals("0")){
                LogUtils.saveLog("自定义短信发送成功",phoneNum+"-"+content+"-"+sendhRes);//定时器短信
                return true;
            }else{
                LogUtils.saveLog("自定义短信发送失败",phoneNum+"-"+content+"-"+sendhRes);//定时器短信
                return false;
            }
        } catch (Exception e) {
            LogUtils.saveLog("自定义短信发送失败",phoneNum+"-"+content+"-");
            e.printStackTrace();
            return false;
        }
    }



    /**
     *验证码发送接口
     * @param phoneNum 手机号码
     * @return 随机数的验证码
     * */
    public static String sendIdentifying(String phoneNum){
        try {
            String msgid = UUIDUtil.getUUID(32).toString(); // 短信id，查询短信状态报告时需要，（可选）
            //JSONHttpClient jsonHttpClient = new JSONHttpClient("http://www.dh3t.com");
            //jsonHttpClient.setRetryCount(1);
            String num=createRandom(true,4);
            String content="您好，您本次的验证码："+num+"，10分钟内有效，切记请勿告知他人。";
            String sendhRes = null;//jsonHttpClient.sendSms(account, password, phoneNum, content, sign, subcode,msgid);
            DaHanTricomMessageBean dtmb=JSON.parseObject(sendhRes,DaHanTricomMessageBean.class);
            if(dtmb.getResult().equals("0")){

                LogUtils.saveLog("验证码短信发送成功",phoneNum+"-"+content+"-"+sendhRes);//定时器短信
                return num;
            }else{
                LogUtils.saveLog("验证码短信发送失败",phoneNum+"-"+content+"-"+sendhRes);//定时器短信
                return null;
            }
        } catch (Exception e) {
            LogUtils.saveLog("验证码短信发送失败",phoneNum+"-"+e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 首字母转大写
     * */
    private static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    /**
     * 随机数生成工具
     * @param numberFlag 是否是纯数字
     * @param length 随机数长度
     * */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

}
