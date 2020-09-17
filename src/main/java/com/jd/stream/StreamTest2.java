package com.jd.stream;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lk
 * @Date 2020/2/28 22:34
 * @Version 1.0
 */
public class StreamTest2 {
    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        int sum = list.stream().filter(s -> s.length() < 10)
                .mapToInt(string -> Integer.valueOf(string)).sum();
        System.out.println(sum);
        System.out.println("--------------分割线1---------------");

        //forEach和peek区别 ,peek可以继续操作 forEach为终极操作
        list.stream().filter(s -> Integer.valueOf(s) > 5)
                .forEach(s -> System.out.println(s));
        System.out.println("------------分割线2-------------");

        List<String> collect = list.stream().filter(s -> Integer.valueOf(s) > 5)
                .peek(s -> System.out.println(s))
                .map(string -> string.concat("l"))
                .collect(Collectors.toList());
        System.out.println(collect);


    }
}
