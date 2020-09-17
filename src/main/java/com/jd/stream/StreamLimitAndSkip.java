package com.jd.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * limit 返回 Stream 的前面 n 个元素；
 * skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）
 *
 * @Author lk
 * @Date 2020/3/2 16:44
 * @Version 1.0
 */
public class StreamLimitAndSkip {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Person("name" + i, i));
        }
        //使用limit/skip 管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的 8 次
        List<String> newList = list.stream()
                .map(Person::getName)
                .limit(8).skip(4)
                .collect(Collectors.toList());
        System.out.println(newList);
        System.out.println("-------------分割线-------------------");

        //对元素排序不能保证运行的次数
        List<Person> people = list.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(people);
    }

    static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            System.out.println(name);   //打印查看执行次数
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
