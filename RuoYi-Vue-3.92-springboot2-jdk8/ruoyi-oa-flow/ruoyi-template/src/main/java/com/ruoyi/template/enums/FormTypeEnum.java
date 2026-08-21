package com.ruoyi.template.enums;

/**
 * 表单类型枚举
 *
 * @Author wocurr.com
 */
public enum FormTypeEnum {

    DYNAMIC("1", "动态表单"),
    BIZ("2", "业务表单"),
    DEFINED("3", "自定义表单"),
    ;

    private final String code;

    private final String name;

    FormTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FormTypeEnum getByCode(String code) {
        for (FormTypeEnum value : FormTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
