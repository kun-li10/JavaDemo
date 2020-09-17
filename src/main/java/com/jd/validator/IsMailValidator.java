package com.jd.validator;

import com.jd.util.MailValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验邮箱
 *
 * @Author lk
 * @Date 2020/5/21 14:03
 * @Version 1.0
 */
public class IsMailValidator implements ConstraintValidator<com.jd.validator.IsMail, String> {

    private boolean requird = false;

    @Override
    public void initialize(com.jd.validator.IsMail constraintAnnotation) {
        this.requird = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (requird) {
            return MailValidatorUtil.isMail(s);
        } else {
            if (!StringUtils.isEmpty(s))
                return MailValidatorUtil.isMail(s);
            else
                return true;
        }
    }
}
