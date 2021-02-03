package com.lu.multiple.config.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 17:25:00
 */
@MapperScan(basePackages = {"com.lu.multiple.modular.*.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory01")
@Configuration
public class MybatisPlusConfiguration {

    @Autowired
    Environment env;

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "db1")
    public DataSource db1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.db1.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.db1.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.db1.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.db1.driver-class-name"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory01(@Qualifier("db1") DataSource db1) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(db1);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath*:com/lu/multiple/modular/**/mapper/**/*.xml"));
        factory.setTypeAliasesPackage("com.lu.multiple.modular.business.model");
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory01") SqlSessionFactory sqlSessionFactory01) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory01); // 使用上面配置的Factory
        return template;
    }

}
