package com.ruoyi.template.enums;

/**
 * 消息通知类型枚举
 *
 * @Author wocurr.com
 */
public enum MessageNoticeTypeEnum {

    SMS("1", "短信"),
    SYSTEM_MSG("2", "站内消息");

    private final String code;
    private final String name;

    MessageNoticeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static MessageNoticeTypeEnum getByCode(String code) {
        for (MessageNoticeTypeEnum value : MessageNoticeTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
