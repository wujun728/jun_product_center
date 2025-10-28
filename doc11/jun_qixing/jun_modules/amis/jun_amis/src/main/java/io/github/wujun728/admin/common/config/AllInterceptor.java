package io.github.wujun728.admin.common.config;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyz
 * @date 2021/3/1 14:14
 */
@Component
@Slf4j
public class AllInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setHeader("X-XSS-Protection","1");
//        response.setHeader("X-Frame-Option","SAMEORIGIN");
//        response.setHeader( "Set-Cookie", "JSESSIONID="+request.getSession().getId()+"; HttpOnly");

        String delay = request.getParameter("_delay");
        if(StringUtils.isNotBlank(delay) && NumberUtil.isNumber(delay)){
            int delayTime = NumberUtil.parseInt(delay);
            if(delayTime > 100 && delayTime < 1000){
                log.info("请求:{},延时:{}毫秒",request.getRequestURI(),delayTime);
                Thread.sleep(delayTime);
            }
        }

        return true;
    }
}
