package com.platform.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "操作日志";
}
