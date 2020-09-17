package com.jd.stream;

import java.util.Optional;

/**
 * @Author lk
 * @Date 2020/3/26 15:17
 * @Version 1.0
 */
public class OptionalTest {

    public static void main(String[] args) {
        String name = "likun";
        Person person = new Person();
        Optional.ofNullable(name).ifPresent(newName -> System.out.println(newName));
        Optional.ofNullable(name).ifPresent(name1 -> {
            person.setName(name1);
        });
        System.out.println(person);

        String like = null;
//        like = Optional.ofNullable(like).orElse("值为空");
//        System.out.println(like);

        System.out.println(16 << 3);//乘以2^3
        System.out.println(16 >> 3);  //除以2^3

        String open = "测试";
        System.out.println(Optional.ofNullable(open).get());
    }
}

class Person {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
