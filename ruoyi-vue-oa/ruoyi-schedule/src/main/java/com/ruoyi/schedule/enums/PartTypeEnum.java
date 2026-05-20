package com.ruoyi.schedule.enums;

/**
 * 日程参与人类型枚举
 *
 * @Author wocurr.com
 */
public enum PartTypeEnum {
    PERSONAL("0", "个人提醒"),
    ORGANIZATION("1", "组织参与");
    private final String code;
    private final String desc;

    PartTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
