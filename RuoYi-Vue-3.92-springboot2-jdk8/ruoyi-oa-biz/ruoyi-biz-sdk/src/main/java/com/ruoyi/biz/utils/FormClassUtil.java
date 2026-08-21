package com.ruoyi.biz.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p> 表单Class转换 </p>
 *
 * @Author wocurr.com
 */
public class FormClassUtil {

    /**
     * 转换对象
     *
     * @param o 对象
     * @param clazz class类型
     * @return T 转换后的对象
     */
    public static <T> T transForm(Object o, Class<T> clazz) {
        ObjectMapper objMapper =  new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objMapper.convertValue(o, clazz);
    }
}
