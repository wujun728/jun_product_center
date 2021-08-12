package com.pearadmin.common.plugin.submit;

import com.alibaba.fastjson.JSON;
import com.pearadmin.common.plugin.submit.annotation.RepeatSubmit;
import com.pearadmin.common.web.domain.response.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Describe: 防止重复提交拦截器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter
{
    /**
     * 前置拦截,进入处理活力前判断当前提交的内容是否重复
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    Result result = new Result();
                    result.setSuccess(false);
                    result.setMsg("不允许重复提交，请稍后再试");
                    result.setCode(500);
                    response.setHeader("Content-type","application/json;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(result));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}
