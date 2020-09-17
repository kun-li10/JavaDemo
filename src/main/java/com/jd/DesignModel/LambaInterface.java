package com.jd.DesignModel;

/**
 * Lamba表达式接口
 *
 * @Author lk
 * @Date 2020/3/31 20:19
 * @Version 1.0
 */
@FunctionalInterface
public interface LambaInterface {

    Integer compare(int a, int b);

    default void test() {
        System.out.println("lamba表达式接口类可以定义default方法!");
    }

    default void test1() {
        System.out.println("default方法第二种");
    }

    static void test3() {
        System.out.println("函数式允许定义静态方法!");
    }

}
