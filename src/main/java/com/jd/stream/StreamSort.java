package com.jd.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对 Stream 的排序通过 sorted 进行，
 * 它比数组的排序更强之处在于你可以首先对 Stream 进行各类
 * map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，
 * 这能帮助程序明显缩短执行时间。
 *
 * @Author lk
 * @Date 2020/3/2 17:14
 * @Version 1.0
 */
public class StreamSort {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Person("name" + i, i));
        }
        List<Person> people = list.stream()
//                .sorted((p1, p2) -> p2.getAge().compareTo(p1.getAge()))  //compareTo前后
//                .sorted(Comparator.comparing(Person::getAge))  //升序
                .limit(3)  //元素前几个
                .sorted(Comparator.comparing(Person::getAge).reversed())  //降序
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

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
