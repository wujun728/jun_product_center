package com.shuogesha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
 
    @Value("${swagger.enable}")
    private boolean enableSwagger;
 
    @Value("${swagger.info.version}")
    private String version;
 
    @Value("${swagger.info.title}")
    private String title;
 
    @Value("${swagger.info.description}")
    private String description;  
  
 
    /**通用接口*/
    @Bean
    public Docket generalApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(JSONObject.class, JSONArray.class)
                .enable(enableSwagger)
                .groupName("管理后台接口列表")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shuogesha.platform.action.admin"))
//                .apis(RequestHandlerSelectors.basePackage("com.shuogesha.*"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(headerInfos());
    }
 
    private List<Parameter> headerInfos(){
        //添加head参数
        List<Parameter> headerParams = new ArrayList<>();
        ParameterBuilder tokenParams = new ParameterBuilder();
        tokenParams.name("shuogesha_auth_id").description("用户登录认证key").modelRef(new ModelRef("String")).parameterType("header").defaultValue("").required(false).build(); 
        headerParams.add(tokenParams.build());
        return headerParams;
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl("http://localhost:8080")
                .description(description)
                .version(version)
                .build();
    }
}