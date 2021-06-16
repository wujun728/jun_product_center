package com.lu.single.core.aop;




import java.lang.annotation.*;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-15 01:33:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LuBootLog {

    String descript() default "";

}
