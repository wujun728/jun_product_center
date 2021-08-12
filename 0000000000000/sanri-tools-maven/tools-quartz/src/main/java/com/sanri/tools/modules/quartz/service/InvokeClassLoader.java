package com.sanri.tools.modules.quartz.service;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InvokeClassLoader {
}
