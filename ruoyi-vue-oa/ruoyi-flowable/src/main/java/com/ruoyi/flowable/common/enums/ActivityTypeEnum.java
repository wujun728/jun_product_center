package com.ruoyi.flowable.common.enums;

/**
 * <p> 流程环节类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum ActivityTypeEnum {

    START("startEvent", "开始节点"),
    END("endEvent", "结束节点"),
    USER_TASK("userTask", "用户任务"),
    SERVICE_TASK("serviceTask", "服务任务"),
    SCRIPT_TASK("scriptTask", "脚本任务"),
    MAIL_TASK("mailTask", "邮件任务"),
    MANUAL_TASK("manualTask", "手动任务"),
    BUSINESS_RULE_TASK("businessRuleTask", "业务规则任务"),
    SEND_EMAIL_TASK("sendEmailTask", "发送邮件任务"),
    PARALLEL_GATEWAY("parallelGateway", "并行网关"),
    EXCLUSIVE_GATEWAY("exclusiveGateway", "排他网关"),
    SEQUENCE_FLOW("sequenceFlow", "连线"),;

    private final String code;

    private final String name;

    ActivityTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ActivityTypeEnum fromCode(String code) {
        for (ActivityTypeEnum value : ActivityTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
