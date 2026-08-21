package com.ruoyi.serial.enums;

/**
 * 编号规则枚举
 * @Author wocurr.com
 */
public enum RuleTypeEnum {
    FIXED("0", "固定值"),
    DATE("1", "日期"),
    SEQ("2", "流水号"),
    ;
    private final String code;
    private final String desc;

    RuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public static RuleTypeEnum getByCode(String code) {
        for (RuleTypeEnum item : RuleTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
