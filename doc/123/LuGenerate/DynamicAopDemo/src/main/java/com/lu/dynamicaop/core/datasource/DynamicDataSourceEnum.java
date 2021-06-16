package com.lu.dynamicaop.core.datasource;

/**
 * @program LuGenerate
 * @description: 动态数据源枚举
 * @author: zhanglu
 * @create: 2019-12-12 08:52:00
 */
public enum DynamicDataSourceEnum {

    master("主库"),
    slave01("从库"),

    ;

    private String descript;

    DynamicDataSourceEnum(String descript) {
        this.descript = descript;
    }
}
