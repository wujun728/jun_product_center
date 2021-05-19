package com.platform.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
