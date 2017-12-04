/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yhl.laoyou.common.utils;

import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.net.ssl.*;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * post方式请求服务器(https协议)
	 *
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static byte[] post(String url, String content, String charset)
			throws NoSuchAlgorithmException, KeyManagementException,
			IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes(charset));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return outStream.toByteArray();
		}
		return null;
	}

	public String doPost(String url,Map<String,String> map,String charset){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Basic aHVhbHUwMDAxOjk5OTk5OTk5OQ==");
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			String nztoken = response.getHeaders("nztoken")[0].getValue();
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	public String doPostGetNzToken(String url,Map<String,String> map,String charset,String namepwd){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String nztoken = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Basic "+Encodes.encodeBase64(namepwd));
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			nztoken = response.getHeaders("nztoken")[0].getValue();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return nztoken;
	}

	public String doGetChronicData(String url,Map<String,String> map,String charset, String nztoken){
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);
			httpGet.setHeader("nztoken", nztoken);
			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	public String doGetChronicTrend(String url,Map<String,String> map,String charset, String nztoken, String trendName){
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);
			httpGet.setHeader("nztoken", nztoken);
			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				HttpEntity entity = response.getEntity();
				InputStream inStream = entity.getContent();
				long picLen = response.getEntity().getContentLength();
				OSSObjectTool.uploadFileInputStream(trendName, picLen, inStream, OSSObjectTool.BUCKET_CONSULT_PIC);
				String mediaURL = OSSObjectTool.getConsultMediaBaseUrl()+ trendName;
				EntityUtils.consume(entity);
				result = mediaURL;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
