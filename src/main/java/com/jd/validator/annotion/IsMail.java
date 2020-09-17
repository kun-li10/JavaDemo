package com.jd.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验邮箱注解
 * @Author lk
 * @Date 2020/5/21 13:59
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMailValidator.class})
public @interface IsMail {

    boolean required() default true; //是否需要传

    String message() default "邮箱格式错误";//提示信息

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
