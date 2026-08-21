package com.ruoyi.worksetting.enums;

/**
 * <p> 委托类型 </p>
 *
 * @Author wocurr.com
 */
public enum EntrustTypeEnum {
    ALL("0", "全部"),
    PART("1", "部分"),;

    private final String code;
    private final String message;

    EntrustTypeEnum(String code, String message) {
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
