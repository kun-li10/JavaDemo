package com.jd.optional;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author lk
 * @Date 2020/3/15 14:10
 * @Version 1.0
 */
public class optional {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 49999; i++) {
            list.add(new Student("name" + i, i));
        }
        long start = System.currentTimeMillis();
        for (Student student : list) {
            System.out.println(student);
        }
        long end = System.currentTimeMillis();

        long streamStart = System.currentTimeMillis();
        list.stream().forEach(System.out::println);
        System.out.println("Stream耗时:" + (System.currentTimeMillis() - streamStart));
        System.out.println("for耗时:" + (end - start));
    }

    static class Student {
        String name;
        Integer age;

        public Student(String name, Integer age) {
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
    }
}
