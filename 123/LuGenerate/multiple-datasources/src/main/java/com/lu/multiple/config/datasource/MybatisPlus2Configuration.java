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
@MapperScan(basePackages = {"com.lu.multiple.modular.*.mapper2"}, sqlSessionFactoryRef = "sqlSessionFactory02")
@Configuration
public class MybatisPlus2Configuration {

    @Autowired
    Environment env;

    @Bean(name = "db2")
    public DataSource db2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.db2.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.db2.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.db2.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.db2.driver-class-name"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory02(@Qualifier("db2") DataSource db2) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(db2);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath*:com/lu/multiple/modular/**/mapper/**/*.xml"));
        factory.setTypeAliasesPackage("com.lu.multiple.modular.business.model");
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory02") SqlSessionFactory sqlSessionFactory02) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory02); // 使用上面配置的Factory
        return template;
    }

}
