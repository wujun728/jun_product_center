package com.ruoyi.biz.enums;

/**
 * <p> 表单字段组件枚举类 </p>
 *
 * @Author wocurr.com
 */
public enum ComponentTypeEnum {
    EL_SELECT("el-select"),
    EL_CASCADER("el-cascader"),
    EL_RADIO_GROUP("el-radio-group"),
    EL_CHECKBOX_GROUP("el-checkbox-group"),
    EL_DATE_PICKER("el-date-picker"),
    EL_TIME_PICKER("el-time-picker"),
    ORG_SELECT("design-dept-select"),
    USER_SELECT("design-user-select"),
    UNKNOWN("unknown");

    private final String tag;

    ComponentTypeEnum(String tag) {
        this.tag = tag;
    }

    public static ComponentTypeEnum fromTag(String tag) {
        for (ComponentTypeEnum type : values()) {
            if (type.tag.equals(tag)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public String getTag() {
        return tag;
    }
}
