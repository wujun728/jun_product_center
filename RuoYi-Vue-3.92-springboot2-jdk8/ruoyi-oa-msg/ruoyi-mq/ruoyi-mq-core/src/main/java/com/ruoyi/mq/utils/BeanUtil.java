package com.ruoyi.mq.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p> bean工具类 </p>
 *
 * @Author wocurr.com
 */
public class BeanUtil {

    /**
     * 注册bean
     *
     * @param applicationContext
     * @param beanName
     * @param bean
     * @param <T>
     */
    public static <T> void registerBean(ApplicationContext applicationContext, String beanName, T bean) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        if (context != null) {
            context.getBeanFactory().registerSingleton(beanName, bean);
        } else {
            throw new IllegalStateException("ApplicationContext is not initialized");
        }
    }
}
