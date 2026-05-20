package com.ruoyi.workfile.enums;

/**
 * 正文启用方式枚举
 * @Author wocurr.com
 */
public enum MainTextUseTypeEnum {
    UPLOAD("0", "用户上传"),
    BOOKMARK("1", "书签替换"),
    ;
    private final String code;
    private final String desc;

    MainTextUseTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static MainTextUseTypeEnum getByCode(String code) {
        for (MainTextUseTypeEnum item : MainTextUseTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
