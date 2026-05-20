package com.ruoyi.serial.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 流水号重置枚举
 * @Author wocurr.com
 */
public enum SeqResetTypeEnum {
    NONE("0", "不重置"),
    DAY("1", "按日"),
    WEEK("2", "按周"),
    MONTH("3", "按月"),
    YEAR("4", "按年"),
    ;
    private final String code;
    private final String desc;

    SeqResetTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public static SeqResetTypeEnum getByCode(String code) {
        for (SeqResetTypeEnum item : SeqResetTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return NONE;
    }

}
