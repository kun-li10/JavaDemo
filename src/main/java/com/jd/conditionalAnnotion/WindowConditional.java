package com.jd.conditionalAnnotion;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现自定义的逻辑条件
 *
 * @Author lk
 * @Date 2020/4/12 10:59
 * @Version 1.0
 */
public class WindowConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        ClassLoader classLoader = context.getClassLoader();
        Environment environment = context.getEnvironment();
        BeanDefinitionRegistry registry = context.getRegistry();
        ResourceLoader resourceLoader = context.getResourceLoader();
        //或许当前额系统名称
        String property = environment.getProperty("os.name");
        System.out.println(environment);
        if (property.contains("Windows")) {
            return true;
        }
        return false;
    }
}
