package com.pearadmin.common.plugin.submit.annotation;

import java.lang.annotation.*;

/**
 * Describe: 表单重复提交注解
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    // @annotation
}