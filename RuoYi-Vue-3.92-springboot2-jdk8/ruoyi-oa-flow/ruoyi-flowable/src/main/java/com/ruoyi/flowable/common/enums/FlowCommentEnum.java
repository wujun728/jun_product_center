package com.ruoyi.flowable.common.enums;

/**
 * 流程意见类型
 *
 * @author wocurr.com
 */
public enum FlowCommentEnum {

    /**
     * 说明
     */
    NORMAL("1", "正常意见"),
    REBACK("2", "退回意见"),
    REJECT("3", "驳回意见"),
    DELEGATE("4", "委派意见"),
    ASSIGN("5", "转办意见"),
    STOP("6", "终止流程"),
    RETURN("7","取回意见" );

    /**
     * 类型
     */
    private final String type;

    /**
     * 说明
     */
    private final String remark;

    FlowCommentEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
}
