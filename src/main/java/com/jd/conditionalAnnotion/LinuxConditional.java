package com.jd.conditionalAnnotion;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 系统为Linux的条件
 *
 * @Author lk
 * @Date 2020/4/12 12:07
 * @Version 1.0
 */
public class LinuxConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //IOC使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if (property.contains("Linux"))
            return true;
        return false;
    }
}
