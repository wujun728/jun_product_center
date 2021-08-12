package com.shuogesha.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.common.model.Token;
import com.shuogesha.common.util.DESUtil;
import com.shuogesha.common.util.MD5Util;
import com.shuogesha.platform.dao.UnifiedUserTokenDao;
import com.shuogesha.platform.entity.UnifiedUserToken;
import com.shuogesha.platform.service.UnifiedUserTokenService;

@Service
public class UnifiedUserTokenServiceImpl implements UnifiedUserTokenService{
	
	@Autowired
	private UnifiedUserTokenDao dao;

	/**
	 * 生成时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return Calendar.getInstance().getTimeInMillis() + "";
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static String getRandom() {
		return new Random().nextInt(999999999) + "";
	}
	
	@Override
	public Token generateToken(String appid, String uid) {
		Token token = new Token();
		token.setAppid(appid);
		// 设置签名
		token.setSignature(uid);
		// 设置时间戳
		token.setTimestamp(getTimeStamp());
		// 设置随机数
		token.setRandom(getRandom());
		// 设置授权码
		token.setAuthorizationCode(getAuthorizationCode(appid,uid));
		return token;
	}

	@Override
	public boolean whetherMemberHasSignedIn(String appid,String signature) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("appid", appid);
		map.put("signature", MD5Util.MD5(signature));
		if(dao.count(map)>0){
		    return true;
		}
		return false;
	}

	@Override
	public String createToken(String appid,String uid) {
//		removeBySignature(appid, encryptSignature(uid)); //退出单个端
		removeByUid(Long.valueOf(uid));//退出安卓和ios的的所有客户端
		//退出所有其他app的认证信息
		Token token = generateToken(appid,uid);
		// 保存用户Token
		String tokenStr = encryptMemberToken(token);
		UnifiedUserToken memberToken = new UnifiedUserToken(token.getAppid(),encryptSignature(token.getSignature()), token);
		dao.saveEntity(memberToken);
		return tokenStr;
	}
 

	@Override
	public void setSelfToken(Token token) {
		UnifiedUserToken memberToken = new UnifiedUserToken(token.getAppid(),encryptSignature(token.getSignature()), token);
		dao.saveEntity(memberToken);
	}

	@Override
	public String reCreateToken(String tokenStr) {
		Token token = analyseEncryptedMemberToken(tokenStr);
		return createToken(token.getAppid(),token.getSignature());
	}

	@Override
	public String encryptSignature(String uid) {
		return DESUtil.encrypt(uid);
	}

	@Override
	public String decryptSignature(String signature) {
		return DESUtil.decrypt(signature);
	}

	@Override
	public String encryptMemberToken(Token token) {
		return DESUtil.encrypt(token.toString());
	}

	@Override
	public Token decryptMemberToken(String tokenStr) {
		String decryptToken = DESUtil.decrypt(tokenStr);
		String[] params = decryptToken.split("&");
		// 分析用户提交过来的Token
		Token token = new Token();
		try {
			for (int i = 0, j = params.length; i < j; i++) {
				String[] currentParams = params[i].split("=");
				String param = currentParams[0];
				switch (param) {
				case "appid":
					token.setAppid(currentParams[1]);
					break;
				case "signature":
					token.setSignature(currentParams[1]);
					break;
				case "timestamp":
					token.setTimestamp(currentParams[1]);
					break;
				case "random":
					token.setRandom(currentParams[1]);
					break;
				case "authorizationCode":
					token.setAuthorizationCode(currentParams[1]);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return token;
	}

	@Override
	public Token analyseEncryptedMemberToken(String tokenStr) {
		Token token = decryptMemberToken(tokenStr);
		return token;
	}

	@Override
	public boolean verifyMemberToken(String tokenStr) {
		// 分析用户需要验证的Token
		Token token = decryptMemberToken(tokenStr);
		// 给用户加密签名
		String encryptSignature = encryptSignature(token.getSignature());
		// 判定是否包含此Token
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("appid", token.getAppid());
		map.put("encryptSignature", MD5Util.MD5(encryptSignature));
		if(dao.count(map)<0){
		    return false;
		}
		// 获取登录的用户Token
		//TODO redis
		UnifiedUserToken memberToken =  (UnifiedUserToken) dao.findBySignature(map);//(encryptSignature);
		if (memberToken!=null&&memberToken.getToken()!=null) {
			Token tokenIner =  decryptMemberToken(memberToken.getToken());
			// 验证是否存在此用户登录的Token
			if (tokenIner != null && (token.toString()).equals(tokenIner.toString())) {
				// 判定时间戳是否过期
//				long currentTime = Calendar.getInstance().getTimeInMillis();
//				long timestamp = Long.valueOf(tokenIner.getTimestamp());
//				// Token有效时间为30分钟
//				long verifyTime = 30 * 60 * 1000;
				//TODO 默认全部是有效的
//				if (currentTime - timestamp > verifyTime) {
//					// 移除过期的Token
//					dao.removeBySignature(token.getAppid(),encryptSignature);
//					return false;
//				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Token getMemberSignedToken(String appid, String signature) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("appid", appid);
		map.put("encryptSignature", MD5Util.MD5(signature));
		if(dao.count(map)>0){
			UnifiedUserToken memberToken =  (UnifiedUserToken) dao.findBySignature(map);
		    return  decryptMemberToken(memberToken.getToken());
		}
		return null;
	}

	@Override
	public String getAuthorizationCode(String appid,String uid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		String token = MD5Util.MD5("appid=" + appid+"uid=" + uid + "&date=" + date);
		return token;
	}

	@Override
	public String getUid(String tokenStr) {
		// TODO 自动生成的方法存根
		String uid =null;
		if (verifyMemberToken(tokenStr)) {
			Token memberToken = decryptMemberToken(tokenStr);
			uid=memberToken.getSignature();
		}
		return uid;
	}

	@Override
	public void removeBySignature(String appid, String signature) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("appid", appid);
		map.put("encryptSignature", MD5Util.MD5(signature));
		dao.removeBySignature(map);
 	}

	@Override
	public void removeByUid(Long uid) {
 		Map<String, Object> map = new HashMap<String, Object>(); 
 		map.put("encryptSignature", MD5Util.MD5(encryptSignature(uid.toString())));
		dao.removeBySignature(map); 
	}
 
}
