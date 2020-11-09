package com.jd.lambda.funtionRef;

import cn.hutool.core.lang.copier.Copier;

import java.util.function.Supplier;

/**
 * 静态方法函数式引用
 * @author lk
 * @version 1.0
 * @date 2020/9/22 21:36
 */
public class Refrece00 {
  public static void main(String[] args) {
    Supplier<String> test=()->Fun.get();
    System.out.println(test.get());

    Supplier<String> supplier = Fun::get;
    System.out.println(supplier.get());


  }
}

class Fun {
  public static String get() {
    return "Fun Get()";
  }
}
