package com.projectm.common;

import cn.hutool.core.util.StrUtil;

import java.util.List;

public class ListUtils {
    public static  <T> T getValue(List<Object> row, int index, Class<T> clazz){
        T result = null;
        Object o = row.get(index);
        if(null == o){
            return null;
        }
        if(o.toString().contains("-")){
            //码表 code-value
            String code = StrUtil.subBefore(o.toString(),"-",false);
            o = code;
        }
        if(clazz.equals(String.class)){
            return (T)o.toString();
        }
        if(clazz.equals(Integer.class)){
            return clazz.cast(Integer.parseInt(o.toString()));
        }
        if(clazz.equals(Long.class)){
            return clazz.cast(Long.parseLong(o.toString()));
        }
        if(clazz.equals(Float.class)){
            return clazz.cast(Float.parseFloat(o.toString()));
        }
        return clazz.cast(o);
    }
}
