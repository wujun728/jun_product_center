package com.ruoyi.todo.enums;

/**
 * 待办类型枚举
 *
 * @Author wocurr.com
 */
public enum TodoTypeEnum {
    TODO("1", "待办"),
    READ("2", "阅办"),
    ;
    private final String code;
    private final String name;

    TodoTypeEnum(String code, String name) {
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
