package me.wuwenbin.data.jdbc.aop;

import me.wuwenbin.data.jdbc.annotation.DynamicDataSource;
import me.wuwenbin.data.jdbc.exception.DataSourceKeyNotExistException;
import me.wuwenbin.data.jdbc.factory.DaoFactory;
import me.wuwenbin.data.jdbc.factory.support.KeyContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Dynamic dataSource controller by AOP
 * <p>
 * Created by wuwenbin on 2017/1/3.
 *
 * @author wuwenbin
 * @since 1.0.0
 */
@Aspect
@Component
public class DataSourceAspect {

    private DaoFactory daoFactory;

    @Autowired
    public DataSourceAspect(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * define point aspect,if a method has {@link DynamicDataSource} annotation,it starts aop method to filter it
     */
    @Pointcut("@annotation(me.wuwenbin.data.jdbc.annotation.DynamicDataSource)")
    public void dynamicDataSourceAspect() {
    }

    /**
     * before method to switch dataSource
     *
     * @param #joinPoint joinPoint
     * @throws NoSuchMethodException
     * @throws DataSourceKeyNotExistException
     */
    @Before("dynamicDataSourceAspect()")
    public void switchDataSource(JoinPoint joinPoint) throws NoSuchMethodException, DataSourceKeyNotExistException {
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        //noinspection unchecked
        Method method = clazz.getMethod(methodName, argClass);
        if (method.isAnnotationPresent(DynamicDataSource.class)) {
            String dataSourceKey = method.getAnnotation(DynamicDataSource.class).value();
            KeyContextHolder.setKey(dataSourceKey);
            daoFactory.determineTargetDao();
        }
    }

    /**
     * after method to switch back dataSource to default
     *
     * @param #joinPoint
     * @throws NoSuchMethodException
     */
    @After("dynamicDataSourceAspect()")
    public void rollbackDataSource2Default(JoinPoint joinPoint) throws NoSuchMethodException {
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        //noinspection unchecked
        Method method = clazz.getMethod(methodName, argClass);
        if (method.isAnnotationPresent(DynamicDataSource.class)) {
            daoFactory.dynamicDao = daoFactory.defaultDao;
            KeyContextHolder.clearKey();
        }
    }
}
