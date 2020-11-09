package com.jd.lambda;

import com.jd.lk.lambaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 20:36
 */
public class LamDemo00 {
  public static void main(String[] args) throws Exception {
    Callable<String> callable =
        new Callable<String>() {
          @Override
          public String call() throws Exception {
            return "Callable";
          }
        };
    System.out.println(callable.call());

    Callable callable2 =
        () -> {
          return "Callable Lambda!";
        };
    System.out.println(callable2.call());

    Callable callable3 = () -> "Lambda Callable3";
    System.out.println(callable3.call());

    System.out.println("-----------------------------------------");
    List<Person> list = new ArrayList<>();
    IntStream.range(0, 10)
        .forEach(
            value -> {
              Person person = new Person();
              person.setName("China " + value);
              person.setAddress("Shanghai " + value);
              list.add(person);
            });

    ConcurrentMap<String, String> map =
        list.stream().collect(Collectors.toConcurrentMap(Person::getAddress, Person::getName));
    System.out.println(map);
  }
}

class Person {
  private String name;
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
