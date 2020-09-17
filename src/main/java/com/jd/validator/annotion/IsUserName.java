package com.jd.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 自定义校验器
 * @Author lk
 * @Date 2020/5/21 13:39
 * @Version 1.0
 */
@Target({TYPE,METHOD,FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsUserNameValidator.class }) //指定校验得类
public @interface IsUserName {

    boolean required() default true; //是否需要传

    String message() default "用户名称错误";//提示信息

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
