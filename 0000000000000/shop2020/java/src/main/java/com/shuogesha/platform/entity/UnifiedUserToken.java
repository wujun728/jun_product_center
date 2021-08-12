package com.shuogesha.platform.entity;

import java.io.Serializable;

import com.shuogesha.common.model.Token;
import com.shuogesha.common.util.DESUtil;
import com.shuogesha.common.util.MD5Util;

public class UnifiedUserToken implements Serializable {
	 
	private static final long serialVersionUID = -6302960967804006020L;
	
	private String id;
	private String appid;
	private String encryptSignature;
	private String token; 
	
	public UnifiedUserToken() {
		super();
	}

	public String getEncryptSignature() {
		return encryptSignature;
	}

	public void setEncryptSignature(String encryptSignature) {
		this.encryptSignature = encryptSignature;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UnifiedUserToken(String appid, String encryptSignature, Token token) {
		super();
		this.id=MD5Util.MD5(token.toString());
		this.appid =appid;
		this.encryptSignature = MD5Util.MD5(encryptSignature);
		this.token = DESUtil.encrypt(token.toString());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
