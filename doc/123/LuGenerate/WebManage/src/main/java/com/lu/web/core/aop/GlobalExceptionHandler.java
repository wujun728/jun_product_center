/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lu.web.core.aop;

import com.lu.core.tips.ErrorTip;
import com.lu.web.core.exception.LuGentrateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program 戒毒执法平台
 * @description:  全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @author: zhanglu
 * @create: 2019-11-21 08:53:00
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(LuGentrateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object hfitsException(LuGentrateException e) {
        return new ErrorTip(e.getCode(), e.getMsg());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object exception(Exception e) {
        return new ErrorTip(e.getMessage());
    }

}
