package com.jd.validator.annotion;

import com.jd.validator.ColorConstraValidator;
import com.jd.validator.Enum.Colors;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/10 9:24
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ColorConstraValidator.class})
public @interface Color {

    // 错误提示信息
    String message() default "颜色不符合规格";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 约束的类型
    Colors[] value();

}
