package com.pearadmin.plugin.framework.datasource.routing;

import com.pearadmin.plugin.framework.datasource.factory.DynamicDataSourceFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource extends AbstractRoutingDataSource -- [就眠仪式]
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * DynamicDataSource How to construct
     */
    public DynamicDataSource(DynamicDataSourceFactory dynamicDataSourceFactory) {
        super.setDefaultTargetDataSource(dynamicDataSourceFactory.primaryDataSource());
        super.setTargetDataSources(dynamicDataSourceFactory.otherDataSource());
        super.afterPropertiesSet();
    }

    /**
     * DetermineCurrentLookupKey
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.get();
    }

}
