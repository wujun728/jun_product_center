package me.wuwenbin.noteblogv5.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 表明是Mybatis的Mapper
 * created by Wuwenbin on 2018/7/20 at 15:12
 *
 * @author wuwenbin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MybatisMapper {

    String value() default "";
}
