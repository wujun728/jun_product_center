package com.framework.common.constant;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String ORGCODE = "organizationcode";

    /**
     * 令牌
     */
    public static final String TOKEN = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    //------- 业务常量配置 ------

    /**
     * 任务优先级--非常紧急
     */
    public static final String VERY_URGENT_STR = "非常紧急";
    public static final Integer VERY_URGENT = 2;

    /**
     * 任务优先级--紧急
     */
    public static final String URGENT_STR = "紧急";
    public static final Integer URGENT = 1;

    /**
     * 任务优先级--普通
     */
    public static final String GENERAL_STR = "普通";
    public static final Integer GENERAL = 0;

    /**
     * 消息通知类型--信息
     */
    public static final String MESSAGE = "message";
    /**
     * 消息通知类型--通知
     */
    public static final String NOTICE = "notice";
    /**
     * 消息通知类型--任务
     */
    public static final String TASK = "task";
    /**
     * 项目任务流转规则前缀
     */
    public static final String PROJECTRULE = "projectrule";
    /**
    * WebSocket前缀
    */
    public static final String WEBSOCKET = "webSocket";
}
