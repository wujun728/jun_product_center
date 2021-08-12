package com.shuogesha.platform.version;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限
 * 
 * @author zhaohaiyuan
 *
 */
//标注这个类它可以标注的位置
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
//标注这个注解的注解保留时期
@Retention(RetentionPolicy.RUNTIME)
//是否生成注解文档
@Documented
public @interface Permission {
	public String role() default "";//默认的角色
	public String value() default "";//默认的权限标示
}
