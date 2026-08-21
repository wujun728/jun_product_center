package com.ruoyi.kbs.enums;

/**
 * <p> 对象类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum ObjectTypeEnum {
    TOPIC("1", "主题"),
    DOCUMENT("2", "文档"),;

    private final String code;
    private final String message;

    ObjectTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ObjectTypeEnum getByCode(String code) {
        for (ObjectTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
