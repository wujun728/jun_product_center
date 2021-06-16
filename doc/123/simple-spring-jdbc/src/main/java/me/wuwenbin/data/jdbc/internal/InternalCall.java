package me.wuwenbin.data.jdbc.internal;

import org.springframework.aop.framework.AopContext;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * created by Wuwenbin on 2017/11/29 at 19:05
 *
 * @author wuwen
 */
public class InternalCall {

    /**
     * 无返回值
     *
     * @param consumer
     * @param <T>
     */
    public static <T> void transfer(Consumer<T> consumer) {
        //noinspection unchecked
        T bean = (T) AopContext.currentProxy();
        consumer.accept(bean);
    }

    /**
     * 有返回值
     *
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R transfer(Function<T, R> function) {
        //noinspection unchecked
        T bean = (T) AopContext.currentProxy();
        return function.apply(bean);
    }
}
