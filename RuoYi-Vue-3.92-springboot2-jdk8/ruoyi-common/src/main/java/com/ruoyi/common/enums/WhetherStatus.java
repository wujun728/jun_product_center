package com.ruoyi.common.enums;

/**
 * [MIG] 是否状态枚举（OA 迁移引入）
 *
 * @author Wujun
 */
public enum WhetherStatus
{
    YES("1", "是"),
    NO("0", "否");

    private final String code;
    private final String info;

    WhetherStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}