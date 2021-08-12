package com.shuogesha.platform.service;

import com.shuogesha.common.model.Token;

public interface UnifiedUserTokenService {
	/**
	 * 生成加密Token
	 * 
	 * @param uid
	 * @return
	 */
	public  Token generateToken(String appid,String uid);
	/**
	 * 判定是否已经登录
	 * 
	 * @param signature
	 * @return
	 */
	public  boolean whetherMemberHasSignedIn(String appid,String signature);
	/**
	 * 创建用户Token
	 * 
	 * @param appid
	 * @param uid 
	 * @return
	 */
	public String createToken(String appid, String uid);
	
	/**
	 * 设置自定义的Token
	 * 
	 * @param username
	 * @param userToken
	 */
	public void setSelfToken(Token token);
	
	/**
	 * 【重新生成】更新Token
	 * 
	 * @param token
	 * @return
	 */
	public String reCreateToken(String tokenStr);
	
	/**
	 * 加密签名
	 * 
	 * @param uid
	 * @return
	 */
	public String encryptSignature(String uid);
	
	/**
	 * 解密签名
	 * 
	 * @param signature
	 * @return
	 */
	public String decryptSignature(String signature);
	
	/**
	 * 加密用户Token
	 * 
	 * @param token
	 * @return
	 */
	public String encryptMemberToken(Token token);
	
	/**
	 * 解析加密用户Token
	 * 
	 * @param token
	 * @return
	 */
	public Token decryptMemberToken(String tokenStr);
	
	/**
	 * 【解密用户Token】分析用户Token
	 * 
	 * @param token
	 * @return
	 */
	public Token analyseEncryptedMemberToken(String tokenStr);
	
	/**
	 * 验证用户Token
	 * 
	 * @param token
	 * @param userToken
	 * @return
	 */
	public boolean verifyMemberToken(String tokenStr);
	
	/**
	 * 获取已经签入的用户Token
	 * 
	 * @return
	 */
	public Token getMemberSignedToken(String appid,String signature);
	
	/**
	 * 根据用户名获取MD5Token
	 * 
	 * @param username
	 * @return
	 */
	public String getAuthorizationCode(String appid,String uid);
	
	/**
	 * 根据Token获取uid
	 * 
	 * @param token
	 * @return
	 */
	public String getUid(String tokenStr);
	
	/**
	 * 删除无效Token
	 * @param signature
	 */
	public void removeBySignature(String appid,String signature);
	/**
	 * 禁用用户
	 * @param Long
	 */
	public void removeByUid(Long integer);
	
 }
