package com.lu.dynamicplus.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 23:13:00
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDbType();
    }
}
