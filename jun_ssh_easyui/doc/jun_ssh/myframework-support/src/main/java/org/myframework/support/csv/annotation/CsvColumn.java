package org.myframework.support.csv.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface CsvColumn {
//	@Deprecated
//	String name() default "";

	/**
	 * 不需要被导出的字段
	 * @return
	 */
	boolean exported() default true;

	/**
	 * 导入时不需要用户填写的字段，由默认值填充或者可以为空
	 * @return
	 */
	boolean imported() default true;

	String type() default "";

	String format() default "";

	String desc() default "";
	
	String defValue() default "";

	int maxLength() default Integer.MAX_VALUE;

	int minLength() default 0;

	boolean required() default  false ;

	String regexp() default "";

	String validator() default "";

	int max() default Integer.MAX_VALUE;

	int min() default 0;
}
