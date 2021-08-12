package com.pearadmin.plugin.framework.datasource.annotation;

import java.lang.annotation.*;

/**
 * Multi-data Annotation 多 数 据 源 切 换 注 解 -- [就眠仪式]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DataSource {

    /**
     * target data source pool name 切 换 数 据 源 的 名 称
     */
    String value();

}
