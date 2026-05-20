package com.ruoyi.im.socket.enums;


public enum CmdTypeEnum {
    /**
     * 心跳
     */
    HEART_BEAT(1, "心跳"),
    /**
     * 强制下线
     */
    FORCE_LOGOUT(2, "强制下线"),
    /**
     * 系统消息
     */
    SYSTEM_MESSAGE(5,"系统消息");


    private final Integer code;

    private final String desc;

    public Integer code() {
        return code;
    }

    CmdTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static CmdTypeEnum fromCode(Integer code) {
        for (CmdTypeEnum typeEnum : values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}

