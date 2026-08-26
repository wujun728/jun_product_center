package com.ruoyi.common.enums;

/**
 * 是否状态
 * 
 * @author ruoyi
 */
public enum WhetherStatus
{
    NO("0", "否"), YES("1", "是");

    private final String code;
    private final String name;

    WhetherStatus(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
}
