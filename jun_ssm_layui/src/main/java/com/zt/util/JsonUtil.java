package com.zt.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by CDHong on 2018/4/21.
 */
@Slf4j
public class JsonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setDateFormat(new SimpleDateFormat("yyy-MM-dd HH:mm:ss"));
    }

    /**
     * 其它对象转为JSON字符串
     * @param object 要转为JSON的对象
     * @return 返回JSON字符串
     */
    public static String object2String(Object object){
        if(object == null){
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("parse Object to String exception . errors:()",e);
        }
        return null;
    }

    /**
     * 其它对象转为JSON字符串
     * @param object 要转为JSON的对象
     * @param dateFormat 日期格式
     * @return 返回JSON字符串
     */
    public static String object2String(Object object,String dateFormat){
       objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
       return object2String(object);
    }

    /**
     * 把对应的字符串转为指定类型对象
     * @param json 字符串
     * @param typeReference 要转换的类型
     * @param <T>
     * @return
     */
    public static <T> T string2Object(String json, TypeReference<T> typeReference){
        if (json ==null || typeReference == null){
            return null;
        }
        try {
            return objectMapper.readValue(json,typeReference);
        } catch (IOException e) {
            log.warn("parse String to Object exception,String:{},TypeReference<T>:{} error:{}",json,typeReference,e);
            return null;
        }
    }

    /**
     * 把对应的字符串转为指定类型对象
     * @param json 字符串
     * @param typeReference 要转换的类型
     * @param dateFormat 转换的日期格式
     * @param <T>
     * @return
     */
    public static <T> T string2Object(String json, TypeReference<T> typeReference,String dateFormat){
        objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
        return string2Object(json,typeReference);
    }
}
