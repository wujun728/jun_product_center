package com.lu.web.core.reqres.response;

import lombok.Data;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-08 10:50:00
 */
@Data
public class ErrorResponseData extends ResponseData{

    /**
     * 异常的具体类名称
     */
    private String exceptionClazz;

    public ErrorResponseData(String message) {
        super(false, ResponseData.DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }

}
