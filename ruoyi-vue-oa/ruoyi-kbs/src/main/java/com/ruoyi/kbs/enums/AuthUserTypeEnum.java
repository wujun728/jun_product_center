package com.ruoyi.kbs.enums;

/**
 * <p> 主题用户所属类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum AuthUserTypeEnum {
    VISUAL_SCOPE("1", "可见范围"),
    OPERATE_TYPE("2", "操作类型"),
    ;

    private final String code;
    private final String message;

    AuthUserTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
