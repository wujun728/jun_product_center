package com.nbclass.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public class CopyUtil {
    private static Logger logger = LoggerFactory.getLogger(CopyUtil.class);
    /**
     * @param source 源
     * @param dest   目标
     */
    public static void copy(Object source, Object dest) {
        try {
            if (source == null || dest == null) {
                return;
            }
            // 获取属性
            BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
            PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
            BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
            PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
                        try {
                            // 调用source的getter方法和dest的setter方法
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                            break;
                        } catch (Exception e) {
                            logger.info("属性赋值失败," + sourceProperty[i].getName() + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("对象赋值失败," + source + "," + dest);
        }
    }

    /**
     * @param source 源
     * @param dest   目标
     */
    public static void copyNotNull(Object source, Object dest) {
        try {
            if (source == null || dest == null) {
                return;
            }
            // 获取属性
            BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
            PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
            BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
            PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName()) && CoreUtils.notNull(sourceProperty[i].getReadMethod().invoke(source))) {
                        try {
                            // 调用source的getter方法和dest的setter方法
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                            break;
                        } catch (Exception e) {
                            logger.info("属性赋值失败," + sourceProperty[i].getName() + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
             logger.info("对象赋值失败," + source + "," + dest);
        }
    }

    /***
     *
     * @param source
     *            源
     * @param clazz
     *            目标类
     * @return 目标类实例
     */
    public static <T> T getCopy(Object source, Class<T> clazz) {
        T dest = null;
        try {
            dest = clazz.newInstance();
            copy(source, dest);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("对象复制错误:" + e.getMessage());
        }
        return dest;
    }

    /***
     *
     * @param source
     *            源
     * @param dest
     *            目标类
     * @param ignoreProperties
     *            过滤掉的属性
     */
    public static Object getCopy(Object source, Object dest, String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(source, dest, ignoreProperties);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("对象复制错误:" + e.getMessage());
        }
        return dest;
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> getCopyList(List sources, Class<T> clazz) {
        List<T> clazzs = new ArrayList<>();
        if (sources == null) {
            return clazzs;
        }
        for (Object source : sources) {
            try {
                T dest = clazz.newInstance();
                copy(source, dest);
                clazzs.add(dest);
            } catch (InstantiationException e) {
                e.printStackTrace();
                logger.info("对象复制错误:" + e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.info("对象复制错误:" + e.getMessage());
            }
        }
        return clazzs;
    }
}