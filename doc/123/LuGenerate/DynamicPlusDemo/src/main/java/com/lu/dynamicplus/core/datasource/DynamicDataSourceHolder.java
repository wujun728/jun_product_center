package com.lu.dynamicplus.core.datasource;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 23:14:00
 */
public class DynamicDataSourceHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DynamicDataSourceEnum.master.name();
        }
        return db;
    }

    public static void setDBType(String str) {
        System.out.println("数据源为" + str);
        contextHolder.set(str);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

}
