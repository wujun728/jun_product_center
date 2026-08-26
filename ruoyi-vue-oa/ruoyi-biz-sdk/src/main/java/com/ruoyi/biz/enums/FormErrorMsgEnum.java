package com.ruoyi.biz.enums;

import lombok.Getter;

/**
 * <p> 表单错误信息枚举 </p>
 *
 * @Author wocurr.com
 */
@Getter
public enum FormErrorMsgEnum {

    UNSUPPORTED_COMPONENT("E1001", "不支持的组件类型"),
    CONVERSION_ERROR("E1002", "转换异常"),
    MISSING_FIELD("E1003", "缺少必要字段"),
    INVALID_OPTIONS("E1004", "选项配置错误");

    private final String code;
    private final String message;

    FormErrorMsgEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}
