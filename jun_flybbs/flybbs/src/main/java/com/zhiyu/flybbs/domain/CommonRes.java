package com.zhiyu.flybbs.domain;

import java.io.Serializable;

/**
 * @author zhiyu
 */
public class CommonRes implements Serializable {
    private static final long serialVersionUID = -8544505200242159869L;

    private boolean success;

    private String message;

    private Object obj;

    public CommonRes(boolean success, String message, Object obj) {
        this.success = success;
        this.message = message;
        this.obj = obj;
    }

    public CommonRes(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonRes(boolean success) {
        this.success = success;
    }

    public static CommonRes build(int result, String successMsg, String failMsg, Object obj) {
        return new CommonRes(result > 0, result > 0 ? successMsg : failMsg, obj);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
