package com.wys.util.bean;

/**
 * Created by wangyushuai@fang.com on 2018/3/15.
 */
public class JsonResult<T> {
    public JsonResult() {
        setCode(100);
        setMessage("成功!");
    }
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
