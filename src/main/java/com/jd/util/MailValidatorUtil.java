package com.jd.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author lk
 * @Date 2020/5/21 14:08
 * @Version 1.0
 */
public class MailValidatorUtil {

    // 验证邮箱
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
            "+[a-zA-Z]{2,}$";

    public static boolean isMail(String mail) {
//        Pattern pattern = Pattern.compile(REGEX_EMAIL);
//        Matcher matcher = pattern.matcher(mail);
//        boolean matches1 = matcher.matches();
        if (StringUtils.isEmpty(mail)) {
            return false;
        }
        boolean matches = Pattern.matches(REGEX_EMAIL, mail);
        return matches;
    }

    public static boolean isEmpty(String mail) {
        return StringUtils.isEmpty(mail);
    }
}
