package com.ruoyi.mq.enums;

/**
 * <p> rabbitmq 交换机模型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum RabbitMqTypeEnum {
    /**
     * 交换机模型
     */
    TOPIC("topic", "topic"),
    DIRECT("direct", "direct"),
    FANOUT("fanout", "fanout"),
    HEADERS("headers", "headers"),
    STREAM("stream", "stream"),
    DEFAULT("default", "default"),
    ;

    private RabbitMqTypeEnum(String code, String name) {
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
    public static RabbitMqTypeEnum getEnum(String code) {
        for (RabbitMqTypeEnum mqTypeEnum : RabbitMqTypeEnum.values()) {
            if (mqTypeEnum.getCode().equals(code)) {
                return mqTypeEnum;
            }
        }
        return null;
    }
}
