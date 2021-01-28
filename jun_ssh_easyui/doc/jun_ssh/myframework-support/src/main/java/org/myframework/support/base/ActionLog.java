package org.myframework.support.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface ActionLog {
	// 操作内容 对应数据库TBL_SYS_ACTION_LOG中的OPERATION_CONTENT
	String content() ;
	// 操作类型 type:可以是菜单或者权限ID 对应数据库TBL_SYS_ACTION_LOG中的OPERATE_TYPE
	String type() default "";
	// 操作描述 对应数据库TBL_SYS_ACTION_LOG中的MODULE_NAME
	String description() default "";

}
