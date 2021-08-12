package com.pearadmin.common.config;

import lombok.extern.slf4j.Slf4j;
import com.pearadmin.common.config.proprety.SwaggerProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Describe: 接 口 文 档 配 置 文 件
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnClass(Contact.class)
@EnableConfigurationProperties(SwaggerProperty.class)
public class SwaggerConfig {

    @Resource
    private SwaggerProperty documentAutoProperties;

    @Bean
    public Docket docker(){
        log.info("Read document configuration information");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(documentAutoProperties.getGroupName())
                .enable(documentAutoProperties.getEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(documentAutoProperties.getScanPackage()))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                documentAutoProperties.getTitle(),
                documentAutoProperties.getDescribe() ,
                documentAutoProperties.getVersion(),
                documentAutoProperties.getTermsOfServiceUrl(),documentAutoProperties.getContact(),documentAutoProperties.getLicence(),documentAutoProperties.getLicenceUrl(),
                new ArrayList<>()
        );
    }
}