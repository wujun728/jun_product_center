package com.ruoyi.biz.enums;

/**
 * <p> 操作类型 </p>
 *
 * @Author wocurr.com
 */
public enum OperateTypeEnum {

    COMPLETE("200", "完成"),

    REJECT("201", "驳回"),

    BACK("202", "直接退回"),

    BACK_ME("203", "退回提交返回我"),

    CLAIM("204", "认领/签收"),

    UNCLAIM("205", "取消认领/签收"),

    COPY("206", "抄送"),

    DELEGATE("207", "委派"),

    RESOLVE("208", "转办"),

    ADD_MULTI("209", "加签"),

    DELETE_MULTI("210", "减签");

    private final String type;

    private final String desc;

    private OperateTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static OperateTypeEnum getByType(String type) {
        for (OperateTypeEnum operateType : values()) {
            if (operateType.getType().equals(type)) {
                return operateType;
            }
        }
        return null;
    }
}
