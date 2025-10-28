package com.jun.plugin.system.common.jwt;

import com.alibaba.fastjson2.JSON;
import com.jun.plugin.common.constant.Constant;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.common.util.JwtUtil;
import com.jun.plugin.common.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebFilter(filterName = "jwtfilter",urlPatterns = "/6666/*")
public class JwtFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
//    private static final String TOKEN = "token";
    @Autowired
    private RedisService redisUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //过滤放行地址
        String url = httpServletRequest.getRequestURI().startsWith("/")?httpServletRequest.getRequestURI().substring(1):httpServletRequest.getRequestURI();
        logger.info("url:{}",url);
        Map<String,Boolean> map = new HashMap<>();
        /**swagger start**/
        map.put("doc.html",true);
        map.put("webjars/bycdao-ui",true);
        map.put("swagger-resources",true);
        map.put("v2/api-docs",true);
        map.put("data/verification/login",true);
        map.put("data/enterpriseFollowMonitorDetail/listAll",true);
        map.put("data/enterpriseFollowMonitorDetail/update",true);
        map.put("data/enterpriseController/enterpriseRecommend",true);
        /**swagger end**/
        map.put("sys/user/login",true);
        map.put("sys/user/token",true);

        /** 业务放行地址**/
//        map.put("data/aiIndustrySystem",true);
        for(String passUrl: map.keySet()){
            if(url.contains(passUrl)){
                filterChain.doFilter(servletRequest,servletResponse);
                logger.info("passUrl:{}",url);
                return;
            }
        }
        String token = httpServletRequest.getHeader(Constant.ACCOUNT);
        if(null==token){
            responseResult(httpServletResponse, DataResult.fail("JWTToken为空，鉴权失败"));
            //throw new BusinessException("token 不合法！");
        }
        if(StringUtils.isNotBlank(token)){
            try {
                if(refreshToken(token)){
                    filterChain.doFilter(servletRequest,servletResponse);
                } else {
                    responseResult(httpServletResponse, DataResult.fail("Token非法，鉴权失败"));
                }
            } catch (BusinessException e) {
                responseResult(httpServletResponse, DataResult.fail(e.getMessage()));
            }
        }
    }

    @Override
    public void destroy() {

    }
    //刷新token时间
    public boolean refreshToken(String token) {
        String tokenKey = "sys:user:token" + token ;
        String cacheToken =(String)redisUtil.get(tokenKey);
        try {
            if (StringUtils.isNotEmpty(cacheToken)) {
                // 校验token有效性，注意需要校验的是缓存中的token
                if (JwtUtil.verify(cacheToken)) {
                    redisUtil.set(tokenKey, cacheToken) ;
                    redisUtil.expire(tokenKey, 30 * 600 * 2);
                    return true;
                }else{
                    return false;
                }
            }else{
                if (JwtUtil.verify(token)) {
                    redisUtil.set(tokenKey, token) ;
                    redisUtil.expire(tokenKey, 30 * 60 * 2);
                    return true;
                }
            }
        } catch (BusinessException e) {
            throw e;
        }
        return false;
    }

    private void responseResult(HttpServletResponse response, DataResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}