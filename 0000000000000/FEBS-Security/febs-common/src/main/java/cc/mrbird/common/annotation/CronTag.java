package cc.mrbird.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CronTag {
    @AliasFor(value = "value",annotation = Component.class)
    String value() default "";
}
