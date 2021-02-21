package com.deer.wms.common.exception;


import com.deer.wms.common.core.result.Code;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class ServiceException extends RuntimeException {
    private Code code;

    public ServiceException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public ServiceException(Code code, String message) {
        super(message);
        this.code = code;
        this.code.setMessage(message);
    }

    public ServiceException(Code code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    /**
     * 获取业务错误代码
     *
     * @return 业务错误代码
     */
    public Code getCode() {
        return code;
    }
}
