package com.jun.plugin.common;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONField;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.exception.code.ResponseCodeInterface;
import lombok.Data;

/**
 * 返回值DataResult
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class Result<T> {

    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final int SUCCESS = 200;//成功
    public static final int FAIL = 400;//失败
    public static final int UNAUTHORIZED = 401;//未认证（签名错误）
    public static final int NOT_FOUND = 404;//接口不存在
    public static final int INTERNAL_SERVER_ERROR = 500;//服务器内部错误
    public static final int  PARAM_FAIL = 10001;//参数异常
	

    /**
     * 请求响应code，0为成功 其他为失败
     */
    private int code;

    /**
     * 响应异常码详细信息
     */
    private String msg;

    @JSONField(serializeFeatures = {JSONWriter.Feature.WriteMapNullValue})
    private T data;
    
//    boolean success;
 

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
        this.msg = null;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }


    public Result() {
        this.code = 0;
        this.msg = "操作成功";
        this.data = null;
    }

    public Result(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "操作成功";
    }
    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    } 

    /**
     * 自定义返回  data为null
     */
    public static Result getResult(int code, String msg) {
        return new Result(code, msg);
    }
    public static Result getResult(ResponseCodeInterface code) {
        return new Result(code.getCode(), code.getMsg());
    }

    

    /**
     * 操作成功 data为null
     */
    public static Result success() {
        return new Result();
    }

    /**
     * 操作成功 data 不为null
     */
    public static Result success(Object data) {
        return new Result(data);
    }

    /**
     * 操作失败 data 不为null
     */
    public static Result fail(String msg) {
        return new Result(500002, msg);
    }
    
    public static Result successWithMsg(String msg) {
    	return new Result(null,0,msg);
    }

    public static Result successWithData(Object data) {
    	return new Result(data,0,"操作成功");
    }

    public static Result successWithDataMsg(Object data, String msg) {
    	return new Result(data,0,msg);
    }


    public Result setCode(int resultCode) {
        this.code = resultCode;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Result setMessage(String message) {
        this.msg = message;
        return this;
    }
    


}
