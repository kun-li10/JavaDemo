package com.jd.lambda.funtionRef;

import io.swagger.models.auth.In;

import java.util.function.BiFunction;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/22 21:49
 */
public class BiFunRef {
  public static void main(String[] args) {

    BiFunction<String, String, Integer> biFunction0 = (a,b)->a.length()+b.length();
    System.out.println(biFunction0.apply("dadsa","fafsa"));

    BiFunction<String, String, Integer> biFunction1 = Fun3::getLength;
    System.out.println(biFunction1.apply("aaa", "bbb"));
  }
}

class Fun3 {
  static Integer getLength(String a, String b) {
    return a.length() + b.length();
  }
}
