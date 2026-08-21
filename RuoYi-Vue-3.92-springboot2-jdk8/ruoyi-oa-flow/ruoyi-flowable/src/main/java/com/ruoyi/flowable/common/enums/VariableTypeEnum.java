package com.ruoyi.flowable.common.enums;

/**
 * <p> 变量类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum VariableTypeEnum {

    STRING("string", "字符串"),
    INTEGER("integer", "整数"),
    DOUBLE("double", "浮点数"),
    BOOLEAN("boolean", "布尔"),
    LONG("long", "长整数"),
    SERIALIZABLE("serializable", "序列化");

    private final String code;

    private final String name;

    VariableTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static VariableTypeEnum fromCode(String code) {
        for (VariableTypeEnum value : VariableTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
