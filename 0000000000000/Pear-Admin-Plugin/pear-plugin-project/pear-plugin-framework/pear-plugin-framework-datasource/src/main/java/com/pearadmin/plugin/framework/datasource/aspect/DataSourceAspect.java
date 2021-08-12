package com.pearadmin.plugin.framework.datasource.aspect;

import com.pearadmin.plugin.framework.datasource.annotation.DataSource;
import com.pearadmin.plugin.framework.datasource.routing.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Multi-data source switching annotations -- [就眠仪式]
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    /**
     * Data source switch entry point
     */
    @Pointcut("@annotation(com.pearadmin.plugin.framework.datasource.annotation.DataSource)"
            + "|| @within(com.pearadmin.plugin.framework.datasource.annotation.DataSource)")
    public void dsPointCut() {
    }

    /**
     * Surround notifications
     */
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);

        if (dataSource != null) {
            log.info("DataSource Change" + dataSource.value());
            DynamicDataSourceContextHolder.set(dataSource.value());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.remove();
        }
    }

    /**
     * Get a solution to the current method or class label
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (targetDataSource != null) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }
}
