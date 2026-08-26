package com.ruoyi.serial.enums;

/**
 * 业务类型枚举
 * @Author wocurr.com
 */
public enum BusinessTypeEnum {
    CONTRACT("HT", "合同"),
    SELL_ORDER("SO", "销售订单"),
    PURCHASE_ORDER("PO", "采购订单"),
    ;
    private final String code;
    private final String desc;

    BusinessTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public static BusinessTypeEnum getByCode(String code) {
        for (BusinessTypeEnum item : BusinessTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
