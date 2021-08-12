package com.wechat.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wechat.bean.UserInfo;
import com.wechat.util.HttpInvoker;

 

/**
 * 用户操作接口
 */
public class User {

	private static final String USER_INFO_URI = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public UserInfo getUserInfo(String accessToken, String openid) throws Exception {
		String geturl = USER_INFO_URI+"?openid="+openid+"&access_token="+accessToken;
		String  jsonStr = HttpInvoker.httpGet(geturl);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if(obj.get("errcode") != null){
				throw new Exception(obj.getString("errmsg"));
			}
			UserInfo user = JSONObject.toJavaObject(obj, UserInfo.class);
			return user;
		}
		return null;
	}
	
}