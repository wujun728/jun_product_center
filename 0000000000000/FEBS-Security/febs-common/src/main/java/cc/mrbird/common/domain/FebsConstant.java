package cc.mrbird.common.domain;

public class FebsConstant {

    private FebsConstant() {
    }

    // excel
    public static final String XLSX_SUFFIX = ".xlsx";
    public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // 图形验证码 session key
    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    // 手机验证码 session key
    public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";
    // QQ 用户信息获取 URL
    public static final String GET_QQ_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    // QQ openId 获取 URL
    public static final String GET_QQ_OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    // QQ 授权接口 URL
    public static final String QQ_AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
    // 获取 QQ AccessToken URL
    public static final String GET_QQ_ACCESSTOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    // 微信用户信息获取 URL
    public static final String GET_WEIXIN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?openid=";
    // 获取微信授权码 URL
    public static final String WEIXIN_AUTHORIZE_URL = "https://open.WeiXin.qq.com/connect/qrconnect";
    // 获取微信 AccessToken URL
    public static final String GET_WEIXIN_ACCESSTOKEN_URL = "https://api.WeiXin.qq.com/sns/oauth2/access_token";
    // 微信 Refresh Token URL
    public static final String WEIXIN_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    // 返回报文头 json格式，编码 utf-8
    public static final String JSON_UTF8 = "application/json;charset=utf-8";
    // 返回 html
    public static final String HTML_UTF8 = "text/html;charset=utf-8";
    // 用户注册 URL
    public static final String FEBS_REGIST_URL = "/user/regist";
    // 权限不足 URL
    public static final String FEBS_ACCESS_DENY_URL = "/access/deny/403";
    // 社交账号绑定成功 URL
    public static final String SOCIAL_BIND_SUCCESS_URL = "/social/bind/success";
    // 社交账号解绑成功 URL
    public static final String SOCIAL_UNBIND_SUCCESS_URL = "/social/unbind/success";
    // 社交账户 openId session key
    public static final String SESSION_KEY_SOCIAL_OPENID = "SESSION_KEY_SOCIAL_OPENID";

    // 第三方 API 接口地址
    public static final String MEIZU_WEATHER_URL = "http://aider.meizu.com/app/weather/listWeather";
    public static final String MRYW_TODAY_URL = "https://interface.meiriyiwen.com/article/today";
    public static final String MRYW_DAY_URL = "https://interface.meiriyiwen.com/article/day";
    public static final String TIME_MOVIE_HOT_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";
    public static final String TIME_MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";
    public static final String TIME_MOVIE_COMING_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";
    public static final String TIME_MOVIE_COMMENTS_URL = "https://ticket-api-m.mtime.cn/movie/hotComment.api";

}
