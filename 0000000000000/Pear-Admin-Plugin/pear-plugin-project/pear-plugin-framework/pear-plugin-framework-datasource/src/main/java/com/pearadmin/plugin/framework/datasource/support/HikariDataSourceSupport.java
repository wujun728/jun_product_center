package com.pearadmin.plugin.framework.datasource.support;

import com.pearadmin.plugin.framework.datasource.entity.HikariDataSourceConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceSupport extends HikariDataSource implements BaseDataSourceSupport {

    public HikariDataSourceSupport(HikariDataSourceConfig config) {
        super.setUsername(config.getUsername());
        super.setPassword(config.getPassword());
        this.setPoolName(config.getPoolName());
    }

    @Override
    public HikariDataSourceSupport build() {
        return this;
    }
}
