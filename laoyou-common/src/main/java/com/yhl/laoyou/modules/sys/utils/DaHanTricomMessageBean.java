package com.yhl.laoyou.modules.sys.utils;

public class DaHanTricomMessageBean {

	private  String msgid;  //消息的UUID
	private  String result; //结果标示
	private  String desc;   //具体信息
	private  String failPhones; //失败的

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFailPhones() {
		return failPhones;
	}

	public void setFailPhones(String failPhones) {
		this.failPhones = failPhones;
	}
}
