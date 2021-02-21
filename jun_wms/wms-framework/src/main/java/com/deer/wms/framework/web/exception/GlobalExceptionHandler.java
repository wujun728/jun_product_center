package com.deer.wms.framework.web.exception;

import javax.servlet.http.HttpServletRequest;

import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.exception.ServiceException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.exception.BusinessException;
import com.deer.wms.common.exception.DemoModeException;
import com.deer.wms.common.utils.ServletUtils;
import com.deer.wms.common.utils.security.PermissionUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 
 * @author deer
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e)
    {
        log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request))
        {
            return AjaxResult.error(PermissionUtils.getMsg(e.getMessage()));
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error/unauth");
            return modelAndView;
        }
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public AjaxResult handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return AjaxResult.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public AjaxResult businessException(BusinessException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult demoModeException(DemoModeException e)
    {
        return AjaxResult.error("演示模式，不允许操作");
    }



    /**
     * 处理业务异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(HttpServletRequest request, ServiceException ex) {
        log.info("service exception : {}", ex.getMessage());
        return ResultGenerator.genFailResult(ex.getCode(), ex.getMessage(), "");
    }

    /**
     * 处理文件上传异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public Result handleMultipartException(HttpServletRequest request, MultipartException e) {

        if (e.getMessage().indexOf("the request was rejected because its size") != -1) {
            log.info("upload file error : {}", e.getMessage());
            return ResultGenerator.genFailResult(CommonCode.UPLOAD_FILE_ERROR);
        }

        log.info("upload file error : {}", e.getMessage());
        return ResultGenerator.genFailResult(CommonCode.UPLOAD_FILE_ERROR);
    }

    /**
     * 捕获和处理 MethodArgumentNotValidException 方法参数无效的异常信息
     *
     * @param request 请求
     * @param e 异常
     * @return 返回异常信息状态码和异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleBindException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.info("method argument not valid exception : ", e.getMessage());
        Map<String, String> data = e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResultGenerator.genFailResult(CommonCode.PARAMETER_ERROR, data);
    }

    /**
     * 处理 Exception 异常
     *
     * @param request
     * @param ex
     * @return
     */
   /* @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        handleLog(request, ex);
        String nowMessage = "Request method '" + request.getMethod() + "' not supported";
        if (ex.getMessage().equals(nowMessage)){
            log.info("http method error, please choose correct method!");
            return ResultGenerator.genFailResult(CommonCode.HTTP_METHOD_ERROR, ex.getMessage());
        }

        log.info("server inernal error, please contact the administrator!");
        return ResultGenerator.genFailResult(CommonCode.SERVER_INERNAL_ERROR, ex.getMessage());
    }*/

    /**
     * 处理日志
     *
     * @param request
     * @param ex
     */
    private void handleLog(HttpServletRequest request, Exception ex) {
        StringBuffer logBuffer = new StringBuffer();
        log.error(ex.getMessage());
        if (request != null) {
            logBuffer.append("  request method=" + request.getMethod());
            logBuffer.append("  url=" + request.getRequestURL());
            logBuffer.append("  params=" + request.getParameterMap());
        }
        if (ex != null) {
            logBuffer.append("  exception:" + ex);
        }
        log.error(logBuffer.toString());
    }
}
