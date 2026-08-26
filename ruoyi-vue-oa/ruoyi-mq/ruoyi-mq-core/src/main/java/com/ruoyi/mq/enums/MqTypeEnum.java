package com.ruoyi.mq.enums;

/**
 * <p> mq类型枚举类 </p>
 *
 * @Author wocurr.com
 */
public enum MqTypeEnum {
    /**
     * mq类型
     */
    RABBITMQ("rabbitmq", "rabbitmq");

    private MqTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public static MqTypeEnum getEnum(String code) {
        for (MqTypeEnum mqTypeEnum : MqTypeEnum.values()) {
            if (mqTypeEnum.getCode().equals(code)) {
                return mqTypeEnum;
            }
        }
        return null;
    }
}
