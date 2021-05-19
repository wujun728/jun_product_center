package com.platform.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * 异常处理器
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@RestControllerAdvice(value = {"com.platform"})
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return R.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error(e.getMessage(), e);
        String msg = generateMessage(e);
        return R.error(msg);
    }

    @ExceptionHandler(ApiRRException.class)
    public Object handleApiRRException(ApiRRException e) {
        HashMap result = new HashMap();
        result.put("errno", e.getErrno());
        result.put("errmsg", e.getErrmsg());
        return result;
    }

    /**
     * 主要功能: 根据异常生成Log日志信息 注意事项:无
     *
     * @param exception 异常信息
     * @return String 日志信息
     */
    private String generateMessage(Exception exception) {
        // 记录详细日志到LOG文件
        String message = "";
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {

            if (stackTraceElement.toString().startsWith("com.platform")) {
                message += "类名：" + stackTraceElement.getFileName() + ";方法："
                        + stackTraceElement.getMethodName() + ";行号："
                        + stackTraceElement.getLineNumber() + ";异常信息:"
                        + exception.getMessage();
                break;
            }
            if (stackTraceElement.toString().startsWith("org.springframework.web.method.annotation")) {
                message += "类名：" + stackTraceElement.getFileName() + ";方法："
                        + stackTraceElement.getMethodName() + ";行号："
                        + stackTraceElement.getLineNumber() + ";异常信息:"
                        + exception.getMessage();
                break;
            }
        }
        exception.printStackTrace();
        return message;
    }
}