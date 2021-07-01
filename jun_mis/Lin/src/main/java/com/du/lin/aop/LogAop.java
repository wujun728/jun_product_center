package com.du.lin.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.du.lin.annotation.BizLog;
import com.du.lin.bean.User;
import com.du.lin.log.LogManager;
import com.du.lin.log.LogTaskFactory;
import com.du.lin.utils.Userinfo;

/**
 * 业务切面
 */
@Aspect
@Component
public class LogAop {


    @Pointcut("@annotation(com.du.lin.annotation.BizLog)")
    public void logCut() {

    }

    @Around("logCut()")
    public Object test(ProceedingJoinPoint point) throws Throwable {

        // 执行方法
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            System.out.println("日志记录出错");
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws NoSuchMethodException, SecurityException {
        //如果用户未登录则不记录日志
        User user = Userinfo.getUser();
        if (user == null) {
            return;
        }

        Signature sign = point.getSignature();
        MethodSignature msign = null;
        if (!(sign instanceof MethodSignature)) {
            throw new IllegalArgumentException("只可用于方法");
        }
        // 获取方法名
        msign = (MethodSignature) sign;
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(msign.getName(), msign.getParameterTypes());
        String methodName = method.getName();
        String className = target.getClass().getName();
        // 获取注解
        BizLog bizLog = method.getAnnotation(BizLog.class);
        String logName = bizLog.value();

        LogManager.getInstance()
                .saveLog(LogTaskFactory.getOperationSuccessTimerTask(user.getId(), className, logName, methodName, Userinfo.getUsername()));

    }

}
