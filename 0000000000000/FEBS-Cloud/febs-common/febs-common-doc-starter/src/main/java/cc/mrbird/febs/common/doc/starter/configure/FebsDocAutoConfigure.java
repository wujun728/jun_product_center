package cc.mrbird.febs.common.doc.starter.configure;

import cc.mrbird.febs.common.doc.starter.properties.FebsDocProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author MrBird
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties(FebsDocProperties.class)
@ConditionalOnProperty(value = "febs.doc.enable", havingValue = "true", matchIfMissing = true)
public class FebsDocAutoConfigure {

    private final FebsDocProperties properties;

    public FebsDocAutoConfigure(FebsDocProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Order(-1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())

                .build().securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }

    private ApiInfo groupApiInfo() {
        String description = String.format("<div style='font-size:%spx;color:%s;'>%s</div>",
                properties.getDescriptionFontSize(), properties.getDescriptionColor(), properties.getDescription());

        Contact contact = new Contact(properties.getName(), properties.getUrl(), properties.getEmail());

        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(description)
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(contact)
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .version(properties.getVersion())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }
}
