package com.pearadmin.plugin.configure.datasource;

import com.pearadmin.plugin.framework.datasource.entity.DruidDataSourceConfig;
import com.pearadmin.plugin.framework.datasource.entity.HikariDataSourceConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSource Application Properties Mapper  -- [就眠仪式]
 */
@Data
@ConfigurationProperties("pear.plugin.datasource")
public class DataSourceAutoProperties {

    /**
     * default user DataSource Configuration
     */
    private String primary;

    /**
     * Druid Configuration List
     */
    private Map<String, DruidDataSourceConfig> druid = new HashMap<>();

    /**
     * Hikari Configuration List
     */
    private Map<String, HikariDataSourceConfig> hikari = new HashMap<>();
}
