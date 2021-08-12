package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.enums.FilterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据范围过滤注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFilter {
    /**
     * 过滤类型
     * @return FilterType
     */
    FilterType filterType() default FilterType.FIELD;// field, join

    /**
     * 过滤字段以及别名
     * @return String
     */
    String filterFieldId() default "creator";

    /**
     * 过滤方法名
     * @return String[]
     */
    String[] filterMethods() default {};

    /**
     * 排除过滤方法名
     * @return
     */
    String[] ruledOutMethods() default {};

    /**
     * 过滤类型为join 的时候 left join的表的sql
     * @return
     */
    String joinSql() default "";
}
