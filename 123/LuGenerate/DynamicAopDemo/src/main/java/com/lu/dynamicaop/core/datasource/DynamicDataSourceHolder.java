package com.lu.dynamicaop.core.datasource;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 23:14:00
 */
public class DynamicDataSourceHolder {

    private static ThreadLocal<DynamicDataSourceEnum> contextHolder = new ThreadLocal<>();

    public static void clear(){
        contextHolder.remove();
    }

    public static DynamicDataSourceEnum get(){
        return contextHolder.get();
    }

    public static void set(DynamicDataSourceEnum dataSourceEnum){
        contextHolder.set(dataSourceEnum);
    }

}
