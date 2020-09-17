package com.jd.validator;

import com.jd.validator.Enum.Colors;
import com.jd.validator.annotion.Color;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义检测color的标识
 * @author lk
 * @version 1.0
 * @date 2020/8/10 9:22
 */
public class ColorConstraValidator implements ConstraintValidator<Color, String> {

    private static final Set<String> COLOR_CONSTRAINTS = new HashSet<String>();

    @Override
    public void initialize(Color constraintAnnotation) {
        Colors[] value = constraintAnnotation.value();
        List<String> list = Arrays.stream(value).map(Enum::name).collect(Collectors.toList());
        COLOR_CONSTRAINTS.addAll(list);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return COLOR_CONSTRAINTS.contains(s);
    }
}
