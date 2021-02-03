package com.lu.single.config.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 17:25:00
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"com.lu.single.modular.*.mapper"})
@Configuration
public class MybatisPlusConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }


    @Bean
    public MyMetaObjectHandler objectHandler(){
        return new MyMetaObjectHandler();
    }

}
