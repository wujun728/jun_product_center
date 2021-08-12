package com.pearadmin.common.exception;

import com.pearadmin.common.exception.base.BusinessException;
import com.pearadmin.common.tools.servlet.ServletUtil;
import com.pearadmin.common.web.domain.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * Describe: 全局异常处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 不 支 持 的 请 求 类 型
     * */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage(),e);
        return Result.failure("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦 截 未 知 的 运 行 时 异 常
     */
    @ExceptionHandler({RuntimeException.class})
    public Result notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return Result.failure("运行时异常:" + e.getMessage());
    }

    /**
     * 权 限 异 常 处 理
     * */
    @ExceptionHandler({AccessDeniedException.class})
    public Object access(HttpServletRequest request,AccessDeniedException e){
        e.printStackTrace();
        if (ServletUtil.isAjax(request))
        {
            return Result.failure("暂无权限");

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/403");
            return modelAndView;
        }
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Result.failure("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e)
    {
        log.error(e.getMessage(), e);
        if (ServletUtil.isAjax(request))
        {
            return Result.failure(e.getMessage());
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
    }

}
