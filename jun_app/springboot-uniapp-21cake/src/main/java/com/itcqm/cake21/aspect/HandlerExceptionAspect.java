package com.itcqm.cake21.aspect;

import com.itcqm.commons.exception.CommonException;
import com.itcqm.commons.exception.HandlerException;
import com.itcqm.commons.util.CommonResult;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * \* Author: CQM
 * \* Date: 2020/8/4
 * \* Time: 8:08
 * \* Description: 继承的统一异常拦截器
 * \
 */
@RestControllerAdvice
public class HandlerExceptionAspect extends HandlerException {


    @Override
    public CommonResult globalException(CommonException exception) {
        return super.globalException(exception);
    }

    @Override
    public CommonResult runtimeException(RuntimeException exception) {
        return super.runtimeException(exception);
    }
}