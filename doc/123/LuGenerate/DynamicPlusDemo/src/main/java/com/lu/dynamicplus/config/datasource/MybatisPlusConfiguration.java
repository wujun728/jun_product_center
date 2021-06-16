package com.lu.dynamicplus.config.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.lu.dynamicplus.core.datasource.DynamicDataSource;
import com.lu.dynamicplus.core.datasource.DynamicDataSourceEnum;
import com.lu.dynamicplus.core.datasource.DynamicDataSourceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 17:25:00
 */
@MapperScan(basePackages = {"com.lu.dynamicplus.modular.*.mapper"})
@Configuration
public class MybatisPlusConfiguration {

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.master")
    public DataSource master() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.slave01")
    public DataSource slave() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master,
                                        @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceEnum.master.name(), master);
        targetDataSource.put(DynamicDataSourceEnum.slave01.name(), slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        return dataSource;
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor();
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory()
            throws Exception {
        //配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //懒加载
//        LazyConnectionDataSourceProxy p = new LazyConnectionDataSourceProxy();
//        p.setTargetDataSource(dataSource(master(), slave()));
        sqlSessionFactory.setDataSource(dataSource(master(), slave()));
        //需要mapper文件时加入扫描，sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //加入上面的两个拦截器
        Interceptor interceptor[] = {paginationInterceptor(), dynamicDataSourceInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        return sqlSessionFactory.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
