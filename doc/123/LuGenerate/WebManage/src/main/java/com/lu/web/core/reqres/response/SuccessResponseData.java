package com.lu.web.core.reqres.response;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-08 10:50:00
 */
public class SuccessResponseData extends ResponseData{

    public SuccessResponseData() {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessResponseData(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponseData(Integer code, String message, Object object) {
        super(true, code, message, object);
    }

}
