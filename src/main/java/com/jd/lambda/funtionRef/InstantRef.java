package com.jd.lambda.funtionRef;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 实例方法引用
 * @author lk
 * @version 1.0
 * @date 2020/9/22 21:55
 */
public class InstantRef {

  public String put(){
    return "put............";
  }

  public void getLength(String str){
    System.out.println(str.length());
  }

  public Integer get(String string){
    return string.length();
  }

  public static void main(String[] args) {
    /**
     * Supplier不接受参数,返回类型指定
     */
    Supplier<String> supplier0 = ()->new InstantRef().put();
    System.out.println(supplier0.get());

    Supplier<String> supplier1 = ()->{return new InstantRef().put();};
    System.out.println(supplier1.get());

    /**
     * 直接实例引用
     */
    Supplier<String> supplier2=new InstantRef()::put;
    System.out.println(supplier2.get());

    /**
     * 接收一个指定类型的参数,不返回void
     */
    Consumer<String> consumer = new InstantRef()::getLength;
    consumer.accept("China");

    /**
     * 接收一个指定类型的参数,返回指定类型的值
     */
    Function<String,Integer> function0 = (str)->{return new InstantRef().get(str);};
    System.out.println(function0.apply("fdasdsad"));

    Function<String,Integer> function1 =(str)->new InstantRef().get(str);
    System.out.println(function1.apply("adsdadsadsa"));

    Function<String,Integer> function = new InstantRef()::get;
    System.out.println(function.apply("China"));
  }
}
