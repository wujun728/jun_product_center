package com.shuogesha.platform.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisEvict {
    /**
     * key生成策略，可填写多个，对应于Hash映射中的field
     * 不填写默认删除以当前类全限定名作为key的Hash映射
     */
    String[] field() default {};

    Class type();
}