package com.ruoyi.kbs.enums;

/**
 * <p> 主题操作类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum OperateTypeEnum {
    EDIT("1", "可编辑"),
    READ("2", "仅查看"),
    ;

    private final String code;
    private final String message;

    OperateTypeEnum(String code, String message) {
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
