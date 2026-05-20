package com.ruoyi.im.chat.enums;


/**
 * 业务消息类型枚举
 */
public enum BusinessMessageType {

    TODO_LIST_REFRESH(1, "待办列表刷新", false),
    TODO_RECEIVE(2, "待办接收通知", false),
    SCHEDULE_REMIND(3, "日程提醒", false),
    ;

    private final Integer code;

    private final String desc;

    private final Boolean insertDbFlag;

    BusinessMessageType(Integer code, String desc, Boolean insertDbFlag) {
        this.code = code;
        this.desc = desc;
        this.insertDbFlag = insertDbFlag;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getInsertDbFlag() {
        return insertDbFlag;
    }

    public static BusinessMessageType fromCode(Integer code) {
        for (BusinessMessageType typeEnum : values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}

