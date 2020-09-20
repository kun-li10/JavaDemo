package com.jd.Test;

import com.spire.ms.System.Collections.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/17 23:38
 */
public class LamToString {
  public static void main(String[] args) {
    List<Student> list = new ArrayList();
    IntStream.range(0, 100)
        .forEach(
            value -> {
              list.add(new Student("username" + value, value));
            });

    String collect =
        list.stream()
            .filter(value -> value.getAge() > 50)
            .map(value -> value.getUsername())
            .collect(Collectors.joining("\n"));
    System.out.println(collect);
  }

  @Data
  @AllArgsConstructor
  static class Student {
    private String username;
    private int age;
  }
}
