package com.ruoyi.plugin.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * 多租户配置
 *
 * @author wangzongrun
 */
@Data
@ConfigurationProperties(prefix = "ruoyi.tenant")
public class TenantConfigProperties {

    /**
     * 维护租户列名称
     */
    private String column = "tenant_id";

    /**
     * 多租户的数据表集合（不忽略的表）
     */
    private Set<String> noIgnoreTables = Collections.emptySet();

}
