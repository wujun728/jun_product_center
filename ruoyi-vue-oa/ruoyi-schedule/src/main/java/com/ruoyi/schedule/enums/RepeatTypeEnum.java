package com.ruoyi.schedule.enums;

/**
 * @Author wocurr.com
 */
public enum RepeatTypeEnum {
    DAILY("1", "每天"),
    WEEKLY("2", "每周"),
    MONTHLY("3", "每月"),
    YEARLY("4", "每年");

    private String code;
    private String desc;

    RepeatTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RepeatTypeEnum getRepeatTypeEnum(String code) {
        for (RepeatTypeEnum value : RepeatTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
