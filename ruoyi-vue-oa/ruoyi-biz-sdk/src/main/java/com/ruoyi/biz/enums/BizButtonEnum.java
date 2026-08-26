package com.ruoyi.biz.enums;

/**
 * <p> 公共按钮枚举 </p>
 *
 * @Author wocurr.com
 */
public enum BizButtonEnum {

    SAVE("save", "保存"),
    SIGN("sign", "签收"),
    STAMP("stamp", "盖章"),
    COPY("copy", "抄送"),
    DELEGATE("delegate", "委派"),
    ASSIGN("assign", "转办"),
    UNSIGNED("unsigned", "取消签收"),
    ADD_MULTI("addMulti", "加签"),
    DELETE_MULTI("deleteMulti", "减签"),
    ;

    private final String code;

    private final String name;

    BizButtonEnum(String code, String name) {
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
