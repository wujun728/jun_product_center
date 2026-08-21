package com.ruoyi.seal.enums;

/**
 * 印章样式枚举
 * @Author wocurr.com
 */
public enum SealStyleEnum {
    CIRCLE("circle", "圆形"),
    OVAL("oval", "椭圆");
    private String code;
    private String message;
    SealStyleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public static SealStyleEnum getByCode(String code) {
        for (SealStyleEnum item : SealStyleEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
