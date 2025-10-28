package io.github.wujun728.admin.common.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import io.github.wujun728.admin.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
//@ConditionalOnMissingBean(name="globalExceptionHandler")
public class GlobalAmisExceptionHandler {


    /**
     * 所有的Exception类型异常都会被捕获
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws Exception
     */
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) throws Exception {
        log.error("common exception:{}", e);
        return Result.error(ExceptionUtil.getRootCauseMessage(e));
    }

    /**
     * 处理校验异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("校验异常：", e);
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return Result.error(objectError.getDefaultMessage());
    }
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    public Result handleNoHandlerFoundException(Exception e) {
        return Result.error("找不到接口");
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Result handleHttpRequestMethodNotSupportedException(Exception e) {
        return Result.error("不支持的请求方式");
    }
}
