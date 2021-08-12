package com.pearadmin.plugin.framework.datasource.support;

import com.alibaba.druid.pool.DruidDataSource;
import com.pearadmin.plugin.framework.datasource.entity.DruidDataSourceConfig;

/**
 * DruidDataSource Support -- [就眠仪式]
 */
public class DruidDataSourceSupport extends DruidDataSource implements BaseDataSourceSupport {

    public DruidDataSourceSupport(DruidDataSourceConfig config) {
        this.setUsername(config.getUsername());
        this.setPassword(config.getPassword());
        this.setUrl(config.getUrl());
        this.setDriverClassName(config.getDriverClassName());
        this.setMaxActive(config.getMaxActive());
        this.setInitialSize(config.getInitialSize());
        this.setName(config.getPoolName());
        this.setMinIdle(config.getMinIdle());
    }

    @Override
    public DruidDataSourceSupport build() {
        return this;
    }

}
