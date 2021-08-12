package cc.mrbird.febs.common.security.starter.configure;

import cc.mrbird.febs.common.security.starter.interceptor.FebsServerProtectInterceptor;
import cc.mrbird.febs.common.security.starter.properties.FebsCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MrBird
 */
public class FebsCloudSecurityInteceptorConfigure implements WebMvcConfigurer {

    private FebsCloudSecurityProperties properties;

    @Autowired
    public void setProperties(FebsCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        FebsServerProtectInterceptor febsServerProtectInterceptor = new FebsServerProtectInterceptor();
        febsServerProtectInterceptor.setProperties(properties);
        return febsServerProtectInterceptor;
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(febsServerProtectInterceptor());
    }
}
