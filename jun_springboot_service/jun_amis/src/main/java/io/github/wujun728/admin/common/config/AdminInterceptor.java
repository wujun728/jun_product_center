package io.github.wujun728.admin.common.config;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.constants.ResultCode;
import io.github.wujun728.admin.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hyz
 * @date 2021/3/1 14:14
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
//    @Resource
//    @Lazy
//    SessionContext sessionContext;

    @Value("${amis.filter.enable:false}")
    private Boolean filterEnable;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(filterEnable){
            return handlerMethod(request, response, handler);
        }
        return true;
    }

    private boolean handlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("拦截url:"+ request.getRequestURI());
        String uri = request.getRequestURI();
        SessionContext sessionContext = SpringUtil.getBean(SessionContext.class);
        UserSession userSession = sessionContext.getSession(request);
        if(uri.startsWith("/websocket")){
            return true;
        }
        if(userSession == null){
            if(isAjax(request, handler)){
                response.setContentType("application/json");
                response.getWriter().println(JSONUtil.toJsonStr(new Result(ResultCode.NotLogin,"登录失效",null)));
            }else{
                //response.sendRedirect("/admin/lyear_pages_login.html?t="+System.currentTimeMillis() );
                response.sendRedirect("/login.html?t="+System.currentTimeMillis() );
            }
            return false;
        }
        if(!SessionContext.hasUrlPermission(uri)){
            if(isAjax(request, handler)){
                response.setContentType("application/json");
                response.getWriter().println(JSONUtil.toJsonStr(new Result(ResultCode.NoAuth,"无此权限",null)));
            }else{
                response.sendRedirect("/admin/lyear_pages_error.html?url="+uri);
            }
            return false;
        }
        //logger.info(userSession.getToken());
        return true;
    }

    protected boolean isAjax(HttpServletRequest request,Object handler){
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Class<?> returnType = handlerMethod.getMethod().getReturnType();
            if(returnType.isAssignableFrom(Result.class)){
                return true;
            }
            if(returnType.isAssignableFrom(String.class)){
                return false;
            }
        }
        String header = getHeader(request,"X-Requested-With");
        String dest = getHeader(request,"Sec-Fetch-Dest");
        String requestURI = request.getRequestURI();
        if(requestURI.contains(".") || "iframe".equals(dest)|| "document".equals(dest) || requestURI.endsWith("/") || requestURI.startsWith("/crud/")){
            return false;
        }
//        String contentType = request.getContentType();
//        if(header != null && header.equals("XMLHttpRequest") || "application/json".equals(contentType)){
//            return true;
//        }
        return true;
    }

    private String getHeader(HttpServletRequest request,String name){
        String header = request.getHeader(name);
        if(StringUtils.isNotBlank(header)){
            return header;
        }
        header = request.getHeader(name.toLowerCase());
        if(StringUtils.isNotBlank(header)){
            return header;
        }
        header = request.getHeader(name.toUpperCase());
        if(StringUtils.isNotBlank(header)){
            return header;
        }
        return header;
    }
}
