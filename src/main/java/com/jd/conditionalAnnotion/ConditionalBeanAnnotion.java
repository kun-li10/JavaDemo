package com.jd.conditionalAnnotion;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解,满足条件才会被注入
 * 如果多个Condition实现类条件,必须同时满足才能注入
 *
 * @Author lk
 * @Date 2020/4/12 10:55
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({WindowConditional.class, LinuxConditional.class})
public @interface ConditionalBeanAnnotion {

}
