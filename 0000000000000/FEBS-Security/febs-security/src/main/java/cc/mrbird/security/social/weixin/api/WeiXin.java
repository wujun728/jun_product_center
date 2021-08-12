package cc.mrbird.security.social.weixin.api;

public interface WeiXin {
    WeiXinUserInfo getUserInfo(String openId);
}
