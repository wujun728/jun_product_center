package com.pearadmin.plugin.framework.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hikari DataSource Config -- [就眠仪式]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HikariDataSourceConfig extends BaseDataSourceConfig {

    private String name;
}
