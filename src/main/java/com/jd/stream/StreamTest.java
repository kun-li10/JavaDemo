package com.jd.stream;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lk
 * @Date 2020/2/28 17:37
 * @Version 1.0
 */
public class StreamTest {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        List<Integer> list1 = arrayList.stream().map(m -> m + 10).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println("----------------华丽分割线---------------------");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 5, 5, 5, 10);
        List<Integer> stream = list.stream().
                map(n -> n * n).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(stream);
        System.out.println("----------------分割2------------------");

        String[] str = new String[]{"a", "b", "c"};
        Stream<String> stringStream = Stream.of(str); //方式1
        Stream<String> arrayStream = Arrays.stream(str);//方式2
        List<String> stringList = stringStream.map(String::toUpperCase).collect(Collectors.toList());
        Set<Integer> set = arrayStream.map(String::hashCode).collect(Collectors.toSet());
        System.out.println(stringList);
        System.out.println(set);
        System.out.println("--------------分割3--------------------");

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        List<Integer> collect = outputStream.map(m -> m * m).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("--------------分割4-------------------");

        Integer[] integers = {1, 2, 3, 4, 5, 6}; //过滤
        Stream<Integer> integerStream = Stream.of(integers);
        Integer[] newInteger = integerStream.filter(m -> m % 2 == 0).toArray(Integer[]::new);
        System.out.println(newInteger[0]);
        System.out.println("---------------分割5-----------------------");

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            people.add(new Person("name" + i, i));
        }
        people.stream().filter(person -> person.getAge() > 5) //过滤加输出
                .forEach(person -> System.out.println(person));
        System.out.println(people.size());
        System.out.println("-------------分割线6-----------------");

        List<Person> personList = new ArrayList<>(); //lamb表达式
        for (int i = 0; i < 10; i++) {
            personList.add(new Person("name" + i, i));
        }
        personList.forEach(person -> System.out.println(person));
        System.out.println("----------分割线7----------------");

        List<String> list2 = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list2.toString());
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
