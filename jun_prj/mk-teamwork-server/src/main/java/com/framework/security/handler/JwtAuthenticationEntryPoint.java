package com.framework.security.handler;

import com.framework.common.ResultCode;
import com.framework.common.ResultJson;
import com.framework.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security.handler
 * @description: 认证异常处理类·
 * @author: lzd
 * @create: 2020-06-26 10:32
 **/
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) {
        //todo your business
        ResultCode resultCode;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            resultCode = ResultCode.USER_ERROR_EXCEPTION;
        } else if (exception instanceof LockedException) {
            resultCode = ResultCode.LOCKED_EXCEPTION;
        } else if (exception instanceof CredentialsExpiredException) {
            resultCode = ResultCode.CREDENTIALS_EXPIRED_EXCEPTION;
        } else if (exception instanceof AccountExpiredException) {
            resultCode = ResultCode.ACCOUNT_EXPIRED_EXCEPTION;
        } else if (exception instanceof DisabledException) {
            resultCode = ResultCode.DISABLED_EXCEPTION;
        } else {
            resultCode = ResultCode.FORBIDDEN;
        }
        log.debug("身份验证失败,异常：{}", exception.getMessage());
        ServletUtils.renderString(httpServletResponse, ResultJson.failure(resultCode).toString());
    }

}

