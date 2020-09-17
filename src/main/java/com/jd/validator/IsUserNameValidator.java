package com.jd.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验类
 *
 * @Author lk
 * @Date 2020/5/21 13:41
 * @Version 1.0
 */
public class IsUserNameValidator implements ConstraintValidator<IsUserName, String> {
    private boolean requird = false;

    @Override
    public void initialize(IsUserName constraintAnnotation) {
        this.requird = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
         if (requird)
              return !StringUtils.isEmpty(s);
         else {
             if (StringUtils.isEmpty(s))
                 return true;
             else
                 return true;
         }
    }
}
