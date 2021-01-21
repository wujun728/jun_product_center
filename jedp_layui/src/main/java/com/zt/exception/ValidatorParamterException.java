package com.zt.exception;

/**
 * Created by CDHong on 2018/4/21.
 */
public class ValidatorParamterException extends RuntimeException {
    public ValidatorParamterException() {
        super();
    }

    public ValidatorParamterException(String message) {
        super(message);
    }

    public ValidatorParamterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorParamterException(Throwable cause) {
        super(cause);
    }

    protected ValidatorParamterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
