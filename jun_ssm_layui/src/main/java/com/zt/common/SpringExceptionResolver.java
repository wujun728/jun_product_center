package com.zt.common;

import com.zt.exception.PermissionException;
import com.zt.exception.ValidatorParamterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理，规范请求json数据，用.json结尾，如果请求页面，则以.page结尾
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURL().toString();
        JsonData result = null;
        String defaultMsg = "System error";

        //如果是请求json数据，且抛出的异常是自己定义的，则返回详细的异常信息，使用jsonView视图解析器进行渲染
        if(url.endsWith(".json") && (e instanceof PermissionException || e instanceof ValidatorParamterException)){
            result = JsonData.fail(e.getMessage());
            return new ModelAndView("jsonView",result.toMap());
        }
        //如果是请求page页面，则直接返回execption.jsp页面，并携带错误信息
        if(url.endsWith(".page")){
            log.error("unknown page exception,url: "+url,e);
            result = JsonData.fail(defaultMsg);
            return new ModelAndView("exception",result.toMap());
        }
        //其它异常统一返回系统错误
        log.error("unknown json exception,url: "+url,e);
        result = JsonData.fail(defaultMsg);
        return new ModelAndView("jsonView",result.toMap());
    }
}
