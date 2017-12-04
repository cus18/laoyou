package com.yhl.laoyou.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.live.model.v20161101.DescribeLiveSnapshotConfigRequest;
import com.aliyuncs.live.model.v20161101.DescribeLiveStreamsPublishListRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yhl.laoyou.common.config.Global;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 阿里云直播工具
 * Created by sunxiao on 2017/7/6.
 */
public class LiveTool {

    private static IAcsClient client = null;

    static {
        String accesskey = Global.getConfig("aliyun.accesskey");
        String secret =  Global.getConfig("aliyun.secret");
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accesskey, secret);
            //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.hlsenior.com"); //添加自定义endpoint。
        client = new DefaultAcsClient(profile);
        //System.setProperty("http.proxyHost", "127.0.0.1"); //此设置用于设置代理，可用fiddler拦截查看http请求，便于调试。
        //System.setProperty("http.proxyPort", "8888");
    }

    /**
     * 获取截图配置
     */
    public static void getSnapshotConfig() {
        DescribeLiveSnapshotConfigRequest describeLiveSnapshotConfigRequest = new DescribeLiveSnapshotConfigRequest();
        describeLiveSnapshotConfigRequest.setDomainName("live.hlsenior.com");
        describeLiveSnapshotConfigRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
        describeLiveSnapshotConfigRequest.setAcceptFormat(FormatType.JSON); //指定api返回格式
        describeLiveSnapshotConfigRequest.setMethod(MethodType.POST); //指定请求方法
        describeLiveSnapshotConfigRequest.setAppName("");
        try {
            HttpResponse httpResponse = client.doAction(describeLiveSnapshotConfigRequest);
            System.out.println(httpResponse.getUrl());
            System.out.println(new String(httpResponse.getContent()));
            //todo something.
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看推流历史
     */
    public static void getLiveStreamsPublishList() {
        DescribeLiveStreamsPublishListRequest describeLiveStreamsPublishListRequest = new DescribeLiveStreamsPublishListRequest();
        describeLiveStreamsPublishListRequest.setDomainName("live.hlsenior.com");
        describeLiveStreamsPublishListRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
        describeLiveStreamsPublishListRequest.setAcceptFormat(FormatType.XML); //指定api返回格式
        describeLiveStreamsPublishListRequest.setMethod(MethodType.POST); //指定请求方法
        describeLiveStreamsPublishListRequest.setAppName("AppName");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startTime = DateUtils.StrToDate("2017-07-18 11:11:11","datetime");
        Date endTime = DateUtils.StrToDate("2017-07-18 17:11:11","datetime");

        describeLiveStreamsPublishListRequest.setStartTime(df.format(startTime));
        describeLiveStreamsPublishListRequest.setEndTime(df.format(endTime));
        try {
            HttpResponse httpResponse = client.doAction(describeLiveStreamsPublishListRequest);
            System.out.println(httpResponse.getUrl());
            System.out.println(new String(httpResponse.getContent()));
            //todo something.
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取鉴权的推流地址
     * @return
     */
    public String getRtmpUrl(){
        long timestap = (new Date().getTime()/1000) + Integer.parseInt(ConstantUtil.LIVE_EFFECTIVETIME);
        String str = "/"+ConstantUtil.LIVE_APPNAME+"/"+ConstantUtil.LIVE_STREAMNAME+"-"+timestap+"-0-0-"+ConstantUtil.LIVE_PRIVATEKEY;
        System.out.println("md5:"+MD5Util.get32bitMD5(str));
        String rtmpUrl = ConstantUtil.LIVE_STREAMNAME+"?vhost="+ConstantUtil.LIVE_VHOST+"&auth_key="+timestap+"-0-0-"+MD5Util.get32bitMD5(str);
        return rtmpUrl;
    }
}
