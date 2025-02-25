package com.jun.plugin.shiro.compoent;

import com.alibaba.fastjson2.JSON;

import com.jun.plugin.common.Result;
import com.jun.plugin.common.constant.Constant;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.util.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义过滤器
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        if(url.toString().endsWith(".js") || url.toString().endsWith(".css")){
            return true;
        }
        if(uri.equals("/")){
            return true;
        }
        try {
            Subject subject = getSubject(servletRequest, servletResponse);
            System.out.println(subject.isAuthenticated() + "");
            System.out.println(HttpContextUtils.isAjaxRequest(request));
            log.info(request.getMethod());
            log.info(request.getRequestURL().toString());
            //从header中获取token
            String token = request.getHeader(Constant.ACCESS_TOKEN);
            //如果header中不存在token，则从参数中获取token
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter(Constant.ACCESS_TOKEN);
                if (StringUtils.isEmpty(token)) {
                    Map<String, Object> urlPara = getParam(request);
                    token = urlPara.get(Constant.ACCESS_TOKEN)==null?null:urlPara.get(Constant.ACCESS_TOKEN).toString();
                }
            }
            if (StringUtils.isEmpty(token)) {
                throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
            }
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(token, token);
            getSubject(servletRequest, servletResponse).login(usernamePasswordToken);
        } catch (BusinessException exception) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                customResponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
            } else if (exception.getMessageCode() == BaseResponseCode.TOKEN_ERROR.getCode()) {
                servletRequest.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
            } else if (exception.getMessageCode() == BaseResponseCode.UNAUTHORIZED_ERROR.getCode()) {
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest, servletResponse);
            }
            return false;
        } catch (AuthenticationException e) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                if (e.getCause() instanceof BusinessException) {
                    BusinessException exception = (BusinessException) e.getCause();
                    customResponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
                } else {
                    customResponse(BaseResponseCode.SYSTEM_BUSY.getCode(), BaseResponseCode.SYSTEM_BUSY.getMsg(), servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest, servletResponse);
            }
            return false;
        } catch (Exception e) {
            if (HttpContextUtils.isAjaxRequest(request)) {
                if (e.getCause() instanceof BusinessException) {
                    BusinessException exception = (BusinessException) e.getCause();
                    customResponse(exception.getMessageCode(), exception.getDetailMessage(), servletResponse);
                } else {
                    customResponse(BaseResponseCode.SYSTEM_BUSY.getCode(), BaseResponseCode.SYSTEM_BUSY.getMsg(), servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest, servletResponse);
            }
            return false;
        }
        return true;
    }

    private static Map<String, Object> getParam(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        Map<String, Object> result = new HashMap<>();
        MultiValueMap<String, String> urlMvp = UriComponentsBuilder.fromHttpUrl(url.toString()).build().getQueryParams();
        urlMvp.forEach((key, value) -> {
            String firstValue = CollectionUtils.isEmpty(value) ? null : value.get(0);
            result.put(key, firstValue);
        });
        return result;
    }

    private void customResponse(int code, String msg, ServletResponse response) {
        try {
            Result result = Result.getResult(code, msg);

            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userJson = JSON.toJSONString(result);
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            log.error("error={}", e, e);
        }
    }

}
