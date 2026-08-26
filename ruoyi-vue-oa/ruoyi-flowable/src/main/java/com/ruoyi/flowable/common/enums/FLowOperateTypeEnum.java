package com.ruoyi.flowable.common.enums;

/**
 * <p> 流程操作类型 </p>
 *
 * @Author wocurr.com
 */
public enum FLowOperateTypeEnum {

    START("1", "发起流程"),

    COMPLETE("2", "完成任务"),

    REJECT("3", "驳回任务"),

    BACK("4", "退回任务"),

    CLAIM("5", "认领/签收任务"),

    UNCLAIM("6", "取消认领/签收任务"),

    COPY("7", "抄送任务"),

    DELEGATE("8", "委派任务"),

    RESOLVE("9", "转办任务"),

    ADD_MULTI("10", "加签任务"),

    DELETE_MULTI("11", "减签任务"),
    ;

    private final String type;

    private final String desc;

    private FLowOperateTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static FLowOperateTypeEnum getByType(String type) {
        for (FLowOperateTypeEnum operateType : values()) {
            if (operateType.getType().equals(type)) {
                return operateType;
            }
        }
        return null;
    }
}
