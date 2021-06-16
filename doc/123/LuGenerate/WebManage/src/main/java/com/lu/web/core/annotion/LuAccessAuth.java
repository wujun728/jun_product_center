package com.lu.web.core.annotion;

import java.lang.annotation.*;

/**
 * @program 戒毒执法平台
 * @description:  戒毒执法平台访问权限(目前是依据，loginname)
 * @author: zhanglu
 * @create: 2019-11-21 08:53:00
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LuAccessAuth {

    //接口名称
    String name() default "";

    //接口标识
    String[] auth() default {};

}
