package com.ruoyi.im.socket.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * <p> json工具类 </p>
 *
 * @Author wocurr.com
 */
public class JsonUtil {

    /**
     * json字符串转换为对象
     *
     * @param text
     * @param clazz
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public static  <T> T decode(String text, Class<T> clazz) throws JsonProcessingException {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(text, clazz);
    }

    /**
     * json对象转换为json字符串
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String encode(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * 转换form
     *
     * @param o
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T transForm(Object o, Class<T> clazz) {
        ObjectMapper objMapper =  new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objMapper.convertValue(o, clazz);
    }
}
