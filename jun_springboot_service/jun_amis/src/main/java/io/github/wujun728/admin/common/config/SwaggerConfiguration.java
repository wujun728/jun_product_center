package io.github.wujun728.admin.common.config;

import io.github.wujun728.admin.page.service.DicService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: laizhenghua
 * @date: 2022/8/30 20:32
 */
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public Docket defaultApiDocket() {
        return new Docket(DocumentationType.OAS_30).groupName("默认接口")
                .select().apis(RequestHandlerSelectors.basePackage("io.github.wujun728.admin.api"))
                .paths(PathSelectors.any()).build()
                .globalRequestParameters(getGlobalRequestParameters())
                //正式环境不开启
                .apiInfo(setApiInfo()).enable(!"prod".equals(profile));
    }
    private ApiInfo setApiInfo() {
        Contact contact = new Contact("jqp低代码", "", "");
        ApiInfo info = new ApiInfo("jqp低代码", "jqp低代码", "v1.0",
                "", contact, "", "", new ArrayList<VendorExtension>());
        return info;
    }

//    @Bean
//    public SwaggerResourcesProvider jqpSwaggerResourcesProvider(DicService dicService){
//        SwaggerResourcesProvider provider = () -> {
//            List<SwaggerResource> list = new ArrayList<>();
//            List<Map<String, Object>> menuTypes = dicService.options("menuType");
//            for(Map<String, Object> item:menuTypes){
//                SwaggerResource resource = new SwaggerResource();
//                resource.setSwaggerVersion("3.0.3");
//                resource.setName(item.get("label")+"");
//                resource.setUrl("/openApi/"+item.get("value"));
//                resource.setLocation(resource.getUrl());
//                list.add(resource);
//            }
//            return list;
//        };
//        return provider;
//    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
//        parameters.add(new RequestParameterBuilder()
//                .name("token")
//                .description("token信息")
//                .in(ParameterType.HEADER)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
        return parameters;
    }
}
