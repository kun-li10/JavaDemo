package com.jd.IOCReflect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动义注入注解
 *
 * @Author lk
 * @Date 2020/4/1 16:33
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfAutowired {

    String value() default "";
}
