package com.framework.common;

/**
 * @author Joetao
 * 状态码
 * Created by jt on 2018/3/8.
 */
public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求参数错误或不完整"),
    JSON_FORMAT_ERROR(400, "JSON格式错误"),
    UNAUTHORIZED(401, "请先进行认证"),
    NOT_FOUND(404, "资源不存在！"),
    METHOD_NOT_ALLOWED(405, "请求方式不支持"),
    NOT_ACCEPTABLE(406, "不可接受该请求"),
    LENGTH_REQUIRED(411, "长度受限制"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "不能满足请求的范围"),
    INTERNAL_SERVER_ERROR(500, "服务器正在升级，请耐心等待"),
    SERVICE_UNAVAILABLE(503, "请求超时"),
    MSG_EXCEPTION(500, "默认消息异常"),

    VERIFY_TOKEN_FAIL(401, "会话过期，重新登录!"),
    LOGIN_EXCEPTION(401, "登录失败!"),
    USER_ERROR_EXCEPTION(1001, "用户名或密码错误!"),
    LOCKED_EXCEPTION(1002, "账号锁定，请联系管理员!"),
    CREDENTIALS_EXPIRED_EXCEPTION(1003, "密码过期，请联系管理员!"),
    ACCOUNT_EXPIRED_EXCEPTION(1004, "账户过期，请联系管理员!"),
    DISABLED_EXCEPTION(1005, "账户被禁用，请联系管理员!"),
    FORBIDDEN(403, "无权限操作资源"),
    OPERATE_ERROR(405, "操作失败，请求操作的资源不存在"),
    TIME_OUT(408, "请求超时"),

    EMAIL_USED(201, "邮箱已被使用!"),
    MOBILE_USED(201, "号码已被使用!"),
    CAPTCHA_EXPIRED(201, "验证码已过期!"),

    SERVER_ERROR(500, "服务器内部错误"),
    ;
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
