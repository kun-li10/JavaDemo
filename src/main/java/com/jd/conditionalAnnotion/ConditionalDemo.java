package com.jd.conditionalAnnotion;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 测试Condition条件注入
 *
 * @Author lk
 * @Date 2020/4/12 10:46
 * @Version 1.0
 */
public class ConditionalDemo {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    @Test
    public void test1() {
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        String property = applicationContext.getEnvironment().getProperty("os.name");
        System.out.println("当前系统名称: " + property);
        System.out.println(map);
    }

}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
