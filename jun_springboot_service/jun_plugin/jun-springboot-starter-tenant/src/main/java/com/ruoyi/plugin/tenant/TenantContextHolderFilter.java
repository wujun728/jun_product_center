package com.ruoyi.plugin.tenant;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangzongrun
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //同时从head跟参数中获取
        String headerTenantId = request.getHeader(TanantContants.TENANT_ID);
        String paramTenantId = request.getParameter(TanantContants.TENANT_ID);

        log.debug("获取header中的租户ID为:{}", headerTenantId);

        if (StrUtil.isNotBlank(headerTenantId)) {
            TenantContextHolder.setTenantId(Integer.parseInt(headerTenantId));
        } else if (StrUtil.isNotBlank(paramTenantId)) {
            TenantContextHolder.setTenantId(Integer.parseInt(paramTenantId));
        } else {
            //默认租户为0
            TenantContextHolder.setTenantId(TanantContants.TENANT_ID_DEFAULT);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            // 清理
            TenantContextHolder.clear();
        }
    }

}
