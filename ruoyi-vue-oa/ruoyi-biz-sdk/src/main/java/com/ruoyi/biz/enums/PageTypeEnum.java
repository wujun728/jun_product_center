package com.ruoyi.biz.enums;

/**
 * <p> 页面类型枚举 </p>
 *
 * @Author wocurr.com
 */
public enum PageTypeEnum {

    DRAFT("0", "草稿"),
    SUBMIT("1", "审批"),
    READ("2", "查看")
    ;

    private final String code;

    private final String name;

    PageTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
