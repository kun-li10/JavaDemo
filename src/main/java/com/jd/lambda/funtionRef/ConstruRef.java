package com.jd.lambda.funtionRef;

import cn.hutool.core.lang.Editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 构造方法引用
 * @author lk
 * @version 1.0
 * @date 2020/9/22 22:27
 */
public class ConstruRef {
  public static void main(String[] args) {

    Supplier<Person> s1=()->new Person();
    s1.get();

    Supplier<Person> s2=Person::new;
    s2.get();

    Supplier<List> s3= ArrayList::new;
    List list = s3.get();

    Supplier<HashSet> s4 = HashSet::new;
    s4.get();

    Consumer<Integer> s5 = Person::new;
    s5.accept(10);
  }
}

class Person{

  private String name;
  private int age;
  public Person(){
    System.out.println("无参构造!");
  }

  public  Person(int age){
    System.out.println("有参构造");
    this.age=age;
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
