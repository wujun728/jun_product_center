package com.ruoyi.biz.enums;

/**
 * <p> 业务类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum BizTypeEnum {

    DYNAMIC("dynamic", "动态表单");

    private final String code;

    private final String name;

    BizTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
