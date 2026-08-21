package com.ruoyi.im.socket.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * bean上下文帮助类
 *
 * @author wocurr.com
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        if (null == context) {
            return null;
        }
        return context.getBean(tClass);
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static <T> T getBean(String beanName, Class<T> beanType) {
        return context.getBean(beanName, beanType);
    }

    /**
     * 注册bean
     *
     * @param beanName bean名称
     * @param bean     bean实例
     */
    public static <T> void registerBean(String beanName, T bean) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) getApplicationContext();
        if (context != null) {
            context.getBeanFactory().registerSingleton(beanName, bean);
        } else {
            throw new IllegalStateException("ApplicationContext is not initialized");
        }
    }

    /**
     * 获取所有指定类型的bean
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return context.getBeansOfType(type);
    }
}
