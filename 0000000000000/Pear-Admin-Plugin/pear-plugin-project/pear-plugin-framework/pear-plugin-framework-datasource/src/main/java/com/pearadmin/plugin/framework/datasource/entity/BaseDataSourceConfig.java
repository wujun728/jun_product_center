package com.pearadmin.plugin.framework.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Basic DataSource Config -- [就眠仪式]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDataSourceConfig implements Serializable {

    /**
     * datasource connection username 连接账户
     */
    private String username;

    /**
     * datasource connection password 密码
     */
    private String password;

    /**
     * datasource connection url 数据库连接
     */
    private String url;

    /**
     * datasource connection driverClassName 使用驱动
     */
    private String driverClassName;

    /**
     * datasource connection poolName 连接池名称
     */
    private String poolName;

    /**
     * datasource connection enable 是否开启
     */
    private Boolean enable;
}
