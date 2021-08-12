package com.shuogesha.async;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class CustomAsyncExceptionHandler  implements AsyncUncaughtExceptionHandler{
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // Here goes your exception handling logic.

    }
}
