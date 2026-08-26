package com.ruoyi.flowable.enums;

import com.ruoyi.flowable.common.enums.FLowOperateTypeEnum;

/**
 * <p> 选人范围枚举 </p>
 *
 * @Author wocurr.com
 */
public enum SelectRangeEnum {
    CORP("corp", "公司"),
    DEPT("dept", "部门"),
    DEPT_LEADER("dept_leader", "部门负责人"),
    ;

    private final String code;
    private final String message;

    SelectRangeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static SelectRangeEnum getByCode(String code) {
        for (SelectRangeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
