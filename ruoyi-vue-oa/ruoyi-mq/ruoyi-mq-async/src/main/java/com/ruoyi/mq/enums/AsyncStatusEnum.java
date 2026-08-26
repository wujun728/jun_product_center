package com.ruoyi.mq.enums;

/**
 * <p> 异步任务执行状态 </p>
 *
 * @Author wocurr.com
 */
public enum AsyncStatusEnum {
    /**
     * mq类型
     */
    WAITING("0", "等待执行"),
    SUCCESS("1", "成功"),
    FAIL("2", "失败");

    private AsyncStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
