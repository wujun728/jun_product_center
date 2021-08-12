package com.shuogesha.common.model;

public class Token {
	private String appid;// 应用ID

	private String signature;// 签名

	private String timestamp;// 时间戳

	private String random;// 随机数

	private String authorizationCode;// 授权码
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	@Override
	public String toString() {
		return "appid=" + appid +"&signature=" + signature + "&authorizationCode="
				+ authorizationCode+ 
				"&timestamp=" + timestamp+
				"&random=" + random;
	}

}
