package com.ruoyi.kbs.enums;

/**
 * <p> 可见范围枚举 </p>
 *
 * @Author wocurr.com
 */
public enum VisualScopeEnum {
    ALL("1", "全部可见"),
    PART("2", "部分可见"),
    OWNER("3", "仅自己可见");

    private final String code;
    private final String message;

    VisualScopeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static VisualScopeEnum getByCode(String code) {
        for (VisualScopeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
