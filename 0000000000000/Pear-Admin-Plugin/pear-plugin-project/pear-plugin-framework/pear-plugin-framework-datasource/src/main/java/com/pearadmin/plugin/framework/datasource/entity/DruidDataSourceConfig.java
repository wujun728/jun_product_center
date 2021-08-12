package com.pearadmin.plugin.framework.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Druid DataSource Config 数 据 源 配 置 信 息 -- [就眠仪式]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DruidDataSourceConfig extends BaseDataSourceConfig {

    /**
     * DataSource Connection InitialSize 初 始 化 连 接 数 量
     */
    private int initialSize;

    /**
     * DataSource Connection MinIdle 最 小 空 闲 数 量
     */
    private int minIdle;

    /**
     * DataSource Connection maxActive 最 大 连 接 数 量
     */
    private int maxActive;

    /**
     * DataSource Connection MaxWait 最 大 等 待 时 长
     */
    private int maxWait;

    /**
     * DataSource Connection timeBetweenEvictionRunsMillis
     */
    private int timeBetweenEvictionRunsMillis;

    /**
     * DataSource Connection minEvictableIdleTimeMillis
     */
    private int minEvictableIdleTimeMillis;

    /**
     * DataSource Connection maxEvictableIdleTimeMillis
     */
    private int maxEvictableIdleTimeMillis;
}
