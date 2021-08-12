package com.shuogesha.platform.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * key的生成策略，支持表达式语言，表达式中多个值用"_"分隔
     * 不填写默认使用当前方法名
     * 例如       #name_#id
     * 最终生成SpEL表达式为     #name+'_'+#id
     */
    String field() default "";

    //JSON序列化的类型
    Class type();

    //默认缓存时间是一天 60*60*24
    long expire() default 86400L;
}