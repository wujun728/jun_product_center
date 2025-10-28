package com.ruoyi.plugin.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.ruoyi.common.mybatis.util.MyBatisUtils;
import com.ruoyi.plugin.tenant.TenantContextHolderFilter;
import com.ruoyi.plugin.tenant.TenantHandler;
import com.ruoyi.plugin.tenant.aop.TenantIgnoreAspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 租户信息拦截
 *
 * @author wangzongrun
 */
@Configuration
@EnableConfigurationProperties(TenantConfigProperties.class)
public class TenantConfiguration {

    @Bean
    public TenantIgnoreAspect tenantIgnoreAspect() {
        return new TenantIgnoreAspect();
    }

    @Bean
    public FilterRegistrationBean<TenantContextHolderFilter> tenantContextWebFilter() {
        FilterRegistrationBean<TenantContextHolderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantContextHolderFilter());
        return registrationBean;
    }

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantConfigProperties properties, MybatisPlusInterceptor interceptor) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new TenantHandler(properties));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }

}
