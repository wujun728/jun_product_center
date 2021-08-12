package com.pearadmin.plugin.configure.document;

import com.pearadmin.plugin.framework.document.bean.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Document AutoConfiguration -- [就眠仪式]
 */
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnClass(Contact.class)
@EnableConfigurationProperties(DocumentAutoProperties.class)
public class DocumentAutoConfiguration {

    @Autowired
    private DocumentAutoProperties documentAutoProperties;

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

    /**
     * 详 细 信 息
     * */
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