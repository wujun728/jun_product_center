package com.lu.dynamicaop.core.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;


/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-12 19:35:00
 */
@Aspect
@Component
public class DynamicDataSourceAop {

    @Pointcut("execution(* com.lu.dynamicaop.modular.business.controller..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void deBefore(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        DynamicDataSourceHolder.set(DynamicDataSourceEnum.master);
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        DynamicDataSourceHolder.clear();
    }

}
