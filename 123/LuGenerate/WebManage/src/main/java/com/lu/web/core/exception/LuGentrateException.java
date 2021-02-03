package com.lu.web.core.exception;

/**
 * @program 戒毒执法平台
 * @description: jdzfgl异常
 * @author: zhanglu
 * @create: 2019-11-21 09:05:00
 */
public class LuGentrateException extends RuntimeException {

    private Integer code;

    private String msg;

    public LuGentrateException(Throwable e) {
        super(e);
        this.code = -1;
    }

    public LuGentrateException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LuGentrateException(Throwable e, Integer code, String msg) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
