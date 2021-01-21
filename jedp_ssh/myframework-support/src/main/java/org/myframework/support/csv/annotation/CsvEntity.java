package org.myframework.support.csv.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
public  @interface CsvEntity {

	String name() default "";
	/**
	 * 导入必须的配置
	 *
	 * @return
	 */
	String sqlKey() default "";

	/**
	 * 是否带表头信息
	 *
	 * @return
	 */
	boolean headerEnabled() default  true ;
	
	String rowFilter() default "";
}
