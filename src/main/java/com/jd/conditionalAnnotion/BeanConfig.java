package com.jd.conditionalAnnotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lk
 * @Date 2020/4/12 11:51
 * @Version 1.0
 */
@Configuration
public class BeanConfig {

    @Bean("window")
//    @ConditionalBeanAnnotion
    @Conditional({WindowConditional.class})
    public Person person1() {
        return new Person("Bill", 11);
    }

    @Bean("linux")
//    @ConditionalBeanAnnotion
    @Conditional({LinuxConditional.class})
    public Person person2() {
        return new Person("Linux", 10);
    }
}
