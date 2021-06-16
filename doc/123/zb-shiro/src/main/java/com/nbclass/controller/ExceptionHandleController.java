package com.nbclass.controller;

import com.nbclass.enums.ResponseStatus;
import com.nbclass.exception.ZbException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @date 2018年7月13日
 * @author superzheng
 */
@ControllerAdvice
public class ExceptionHandleController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);

    @ExceptionHandler(ZbException.class)
    public String handleZb(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code",ResponseStatus.ERROR.getCode());
        Map<String,Object> map = new HashMap<>(2);
        map.put("status", ResponseStatus.ERROR.getCode());
        map.put("msg", StringUtils.isNotBlank(e.getMessage())? e.getMessage() : ResponseStatus.ERROR.getMessage());
        logger.error(e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }
    
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuth(HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code",ResponseStatus.FORBIDDEN.getCode());
        return "forward:/error";
    }


}
