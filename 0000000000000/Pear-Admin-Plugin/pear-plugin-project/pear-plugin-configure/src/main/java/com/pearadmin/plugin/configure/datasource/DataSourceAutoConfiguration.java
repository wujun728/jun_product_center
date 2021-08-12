package com.pearadmin.plugin.configure.datasource;

import com.pearadmin.plugin.framework.datasource.entity.BaseDataSourceConfig;
import com.pearadmin.plugin.framework.datasource.factory.DynamicDataSourceFactory;
import com.pearadmin.plugin.framework.datasource.routing.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSource AutoConfiguration -- [就眠仪式]
 */
@Slf4j
@Configuration
@ConditionalOnClass(BaseDataSourceConfig.class)
@EnableConfigurationProperties(DataSourceAutoProperties.class)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
public class DataSourceAutoConfiguration {

    /**
     * Use DataSourceAutoProperties
     *
     * 自 动 配 置 文 件
     */
    @Resource
    private DataSourceAutoProperties properties;

    /**
     * Instantiating DynamicDataSource Bean
     *
     * 初 始 化 多 数 据 源
     */
    @Bean
    public DynamicDataSource datasource(DynamicDataSourceFactory dataSourceFactory) {
        return new DynamicDataSource(dataSourceFactory);
    }

    /**
     * Instantiating DynamicDataSourceFactory Bean
     *
     * 数 据 源 工 厂
     */
    @Bean
    public DynamicDataSourceFactory dataSourceFactory() {
        log.info("Read datasource configuration information");
        //log.info("读 取 数 据 库 连 接 池 配 置 信 息");
        Map<String, BaseDataSourceConfig> dataSourceConfigMap = new HashMap<>();
        dataSourceConfigMap.putAll(properties.getDruid());
        dataSourceConfigMap.putAll(properties.getHikari());
        if(dataSourceConfigMap.size() == 0){
            throw new RuntimeException("数 据 源 配 置 不 能 为 空");
        }
        return new DynamicDataSourceFactory(dataSourceConfigMap, properties.getPrimary());
    }
}
