package com.ruoyi.schedule.enums;

/**
 * 日程提醒时间枚举
 *
 * @Author wocurr.com
 */
public enum RemindTimeEnum {
    NONE("0", 0, "无"),
    FIF_MINUTE("1", 5,"5分钟"),
    TEN_MINUTE("2", 10, "10分钟"),
    FIFTEEN_MINUTE("3", 15, "15分钟"),
    THIRTY_MINUTE("4", 30, "30分钟"),
    ONE_HOUR("5", 60, "1小时"),
    ONE_DAY("6", 1440, "1天"),
    ;
    private final String code;

    private final Integer minute;
    private final String desc;

    RemindTimeEnum(String code, Integer minute, String desc) {
        this.code = code;
        this.minute = minute;
        this.desc = desc;
    }

    public static RemindTimeEnum getRemindTimeEnum(String code) {
        for (RemindTimeEnum value : RemindTimeEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static Integer getBeforeMinute(String code) {
        RemindTimeEnum remindTimeEnum = getRemindTimeEnum(code);
        if (remindTimeEnum != null) {
            return remindTimeEnum.minute;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public Integer getMinute() {return minute;}
    public String getDesc() {
        return desc;
    }
}
