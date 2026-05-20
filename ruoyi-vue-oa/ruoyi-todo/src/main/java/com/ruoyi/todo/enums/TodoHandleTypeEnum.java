package com.ruoyi.todo.enums;

/**
 * 待办操作类型枚举
 *
 * @Author wocurr.com
 */
public enum TodoHandleTypeEnum {
    DRAFT("0", "草稿"),
    AUDIT("1", "审批"),
    REJECT("2", "驳回"),
    BACK("3", "退回"),
    REVOKE("4", "取回"),
    MULTI_REVOKE("5", "多实例取回"),
    COPY("6", "抄送"),
    DELEGATE("7", "委派"),
    ASSIGN("8", "转办"),
    ADD_MULTI("9", "加签"),
    ENTRUST("10", "委托"),
    SECRETARY("11", "秘书办理"),
    ;
    private final String code;
    private final String name;

    TodoHandleTypeEnum(String code, String name) {
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
