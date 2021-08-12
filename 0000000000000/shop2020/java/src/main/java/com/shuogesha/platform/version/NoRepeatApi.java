package com.shuogesha.platform.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 重复提交
 * @author zhaohaiyuan
 *
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatApi {
    /**
     * 默认redis超时时间5秒
     *
     * @return
     */
    int timeout() default 5;
}
