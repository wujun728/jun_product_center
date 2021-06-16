package com.lu.core.tips;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorTip extends Tip {

    public static String OP_FAIL = "操作失败!";
    public static String UPLOAD_FAIL = "上传失败!";

    private Object data;

    public ErrorTip() {
        super();
        this.code = -1;
        this.message = OP_FAIL;
    }

    public ErrorTip(String message) {
        super();
        this.code = -1;
        this.message = message;
    }

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
