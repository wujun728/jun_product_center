package com.lu.single.core.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @program LuGenerate
 * @description: 日志打印
 * @author: zhanglu
 * @create: 2019-12-15 01:35:00
 */
@Slf4j
@Aspect
@Component
public class LuBootLogAspect {

    //换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Pointcut("@annotation(com.lu.single.core.aop.LuBootLog)")
    public void luLog() {
    }

    /**
     * 􏰲􏴑􏵞􏴆􏳛􏵟􏰤x
     *
     * @param joinPoint * @throws Throwable
     */
    @Before("luLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //􏳅􏱽􏳏􏳐􏴿􏵀开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取注解描述信息
        String methodDescription = getAspectLogDescription(joinPoint);
        //打印请求相关参数
        log.info("========================================== Start ==========================================");
        log.info("URL               : {}", request.getRequestURL().toString());
        log.info("Descript          : {}", methodDescription);
        log.info("HTTP MEthod       : {}", request.getMethod());
        log.info("Class Method      : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("IP                : {}", request.getRemoteAddr());
        log.info("Request Args      : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    @After("luLog()")
    public void doAfter() throws Throwable {
        log.info("=========================================== END ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 􏲕􏴍
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("luLog()()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Response Args     : {}", JSON.toJSONString(result));
        log.info("Time-Consuming    : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 获取切面注解描述
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(LuBootLog.class).descript());
                    break;
                }
            }
        }
        return description.toString();
    }

}
