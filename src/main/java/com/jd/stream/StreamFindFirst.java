package com.jd.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * stream findFirst
 *
 * @Author lk
 * @Date 2020/2/29 10:55
 * @Version 1.0
 */
public class StreamFindFirst {

    public static void main(String[] args) {
        String strA = "abc", strB = null, strC = "";
        print(strA);
        print(strB);
        print(strC);
        System.out.println(getLength(strA));
        System.out.println(getLength(strB));
        System.out.println(getLength(strC));

        List<Person> list = new ArrayList<>();
        list.add(new Person("likun", 26));
        list.add(null);
        list.stream()
                .map(person -> Optional.ofNullable(person).map(Person::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void print(String test) {
        // pre - java8
        if (test != null)
            System.out.println("pre-java8: " + test);
        //java 8
        Optional.ofNullable(test).map(s -> "java8:" + s).ifPresent(System.out::println);
    }

    public static int getLength(String test) {
        //pre - java8
//        return test != null ? test.length() : -1;
        //java 8
//        return Optional.ofNullable(test).map(String::length).orElse(-1);
        return Optional.ofNullable(test)
                .filter(s -> s.length() > 0)
                .map(s -> "java8" + s)
                .map(String::length)
                .orElse(-1);
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
