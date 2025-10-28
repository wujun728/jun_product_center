package com.ruoyi.common.core.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应信息主体
 * 
 * @author ruoyi
 */
public class R extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 R 对象，使其表示一个空消息。
     */
    public R()
    {
    }

    /**
     * 初始化一个新创建的 R 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     */
    public R(Type type, String msg)
    {
        super.put(CODE_TAG, type.value());
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 R 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public R(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value());
        super.put(MSG_TAG, msg);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 初始化一个新创建的 R 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public R(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static R ok()
    {
        return R.ok("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static R ok(Object data)
    {
        return R.ok("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static R ok(String msg)
    {
        return R.ok(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static R ok(String msg, Object data)
    {
        return new R(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static R warn(String msg)
    {
        return R.warn(msg, null);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static R warn(String msg, Object data)
    {
        return new R(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static R error()
    {
        return R.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 错误消息
     */
    public static R error(String msg)
    {
        return R.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static R error(String msg, Object data)
    {
        return new R(Type.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static R error(int code, String msg)
    {
        return new R(code, msg, null);
    }

    /**
     * 方便链式调用
     * 
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public R put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}