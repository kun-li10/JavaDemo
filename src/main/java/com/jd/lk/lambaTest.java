package com.jd.lk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * interface只有一个接口时可以直接使用函数式
 *
 * @Author lk
 * @Date 2020/4/9 8:52
 * @Version 1.0
 */
@FunctionalInterface
public interface lambaTest {
    String lam();

    default void test0() {
        System.out.println("test1");
    }

    default void test1() {
        System.out.println("test2");
    }
}

class lamMain {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        while (true) {
            integer.incrementAndGet();
            if (integer.toString().equalsIgnoreCase("10")) {
                break;
            }
            run(() -> {
                return "lambaTest测试!" + integer;
            });
        }
    }

    static void run(lambaTest test) {
        String lam = test.lam();
        System.out.println(lam);
        test.test0();
        test.test1();
    }
}
