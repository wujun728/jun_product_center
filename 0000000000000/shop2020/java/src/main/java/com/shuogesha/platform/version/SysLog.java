package com.shuogesha.platform.version;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 系统日志记录
 * @author zhaohaiyuan
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Documented
public @interface SysLog {
    String description() default "";
    String type() default com.shuogesha.platform.entity.SystemLog.PC;
}
