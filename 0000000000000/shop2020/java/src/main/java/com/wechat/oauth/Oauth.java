package com.wechat.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.wechat.bean.UserInfo;
import com.wechat.util.ConfKit;
import com.wechat.util.HttpInvoker;

 /**
 * 微信Oauth和支付工具类
 *
 */
public class Oauth {

    private static final String LOGIN_URI = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
    private static final String TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private String appid;
    private String secret;
    
	public String callback_URL=null;


    public Oauth() {
        super();
        this.appid = ConfKit.get("AppId");
        this.secret = ConfKit.get("AppSecret");
    }

    public Oauth(String appid, String secret) {
        super();
        this.appid = appid;
        this.secret = secret;
    }
    
    /**
     * 网页版登录
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getAuthorizeURL() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("response_type", "code");
        params.put("redirect_uri", getCallback_URL());
        params.put("scope", "snsapi_userinfo,snsapi_login,snsapi_base");  
        params.put("state", "wx#wechat_redirect");
        String para = PaySign.createSign(params, false);
        return LOGIN_URI + "?" + para;
    }
    
    public UserInfo getUserInfo(String accessToken) throws Exception {
    	User user = new User();
    	return user.getUserInfo(accessToken, getAppid());
    }

    /**
     * 请求code
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getCode() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("response_type", "code");
        params.put("redirect_uri", getCallback_URL());
        params.put("scope", "snsapi_base"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "wx#wechat_redirect");
        String para = PaySign.createSign(params, false);
        return CODE_URI + "?" + para;
    }

    
    /**
     * 通过code 换取 access_token
     * @param code
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public String getWebToken(String code) throws Exception {
        String geturl = TOKEN_URI+"?appid="+getAppid()+"&secret="+getSecret()+"&code="+code+"&grant_type=authorization_code";
        return HttpInvoker.httpGet(geturl);
    }

    /**
     * 刷新 access_token
     * @param refreshToken
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public String getRefreshToken(String refreshToken) throws Exception {
        String geturl = REFRESH_TOKEN_URI+"?appid="+getAppid()+"&refresh_token="+refreshToken+"&grant_type=refresh_token";
        return HttpInvoker.httpGet(geturl);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public String getCallback_URL() {
		if(callback_URL==null){
			return URLEncoder.encode(ConfKit.get("redirect_uri"));
		}
		return URLEncoder.encode(ConfKit.get("redirect_uri")+"?callback_URL="+callback_URL);
	}

	public void setCallback_URL(String callback_URL) {
		this.callback_URL = callback_URL;
	}

}
