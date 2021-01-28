package com.zt.interceptor;

import com.zt.holder.RequestHolder;
import com.zt.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by CDHong on 2018/4/21.
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.endsWith(".page") || url.endsWith(".json")){
            Map parameterMap = request.getParameterMap();
            log.info("request start . url:{} ，params:{}",url, JsonUtil.object2String(parameterMap));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除ThreadLoad中存储的信息
        RequestHolder.removeHolder();
    }
}
