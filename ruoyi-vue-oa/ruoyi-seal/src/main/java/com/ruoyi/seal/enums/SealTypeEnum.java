package com.ruoyi.seal.enums;

/**
 * 印章类型枚举
 * @Author wocurr.com
 */
public enum SealTypeEnum {
    COMPANY_SEAL("0", "公章"),
    CONTRACT_SEAL("1", "合同章"),
    FINANCE_SEAL("2", "发票章"),
    ;
    private String code;
    private String desc;
    SealTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public static SealTypeEnum getByCode(String code) {
        for (SealTypeEnum item : SealTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
