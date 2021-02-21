package com.deer.wms.common.core.result;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * Created by Floki on 2017/9/28.
 */
public class Result<T> {
    /**
     * 业务处理的状态代码
     */
    private int code;

    /**
     * 业务处理的状态提示信息
     */
    private String msg;

    /**
     * 业务处理的返回状态
     */
    private T data;

    public Result setCode(Code code) {
        this.code = code.getCode();
        this.msg = code.getMessage();
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}