package com.pearadmin.plugin.framework.datasource.support;

import javax.sql.DataSource;

/**
 * 数 据 源 创 建 接 口 规 范 -- [就眠仪式]
 * */
public interface BaseDataSourceSupport {

    public DataSource build();
}
