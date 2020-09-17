package com.jd.lk;


import java.util.Random;

/**
 * @Author lk
 * @Date 2020/2/28 10:48
 * @Version 1.0
 */
public class MathUtils {

    public static void main(String[] args) {
        System.out.println(Math.ceil(5.2));//返回第一个大于等于参数的整数
        System.out.println(Math.floor(2.5));//返回第一个小于等于参数的整数
        System.out.println(Math.rint(2.7));//返回参数最接近的整数
        System.out.println(Math.round(2.5f));//返回参数加0.5后最接近的整数
        System.out.println(Math.round(2.8));//参数加0.5后最接近的整数，并强制转换为长整型
        System.out.println("-----------分割线1----------------");
        //比较大小
        System.out.println(Math.max(3, 5));
        System.out.println(Math.min(10L, 11L));
        System.out.println(Math.abs(-10.32323));//返回参数的绝对值
        System.out.println("---------------分割线2-----------------");
        //随机数
        System.out.println(Math.random()); // 0<=random<1随机数
        System.out.println(10 + (Math.random() * 10));  //返回 10<=result<20

        System.out.println("------------分割线3----------------------");
        System.out.println(new Random(100).nextInt());
        System.out.println(new Random().nextInt(100)); //返回0<=result<100的随机数
        System.out.println(new Random().nextBoolean()); //随机布尔的值
        System.out.println(new Random().nextDouble()); //随机双精度值
        System.out.println(new Random().nextFloat());//随机数浮点值
        System.out.println(new Random().nextGaussian());//随机概率密度为高斯分布的双精度
        System.out.println("------------分割线4--------------");
        System.out.println(new Random().nextInt(100));
        System.out.println(new Random().nextFloat());
        System.out.println(new Random().nextDouble());
        System.out.println(new Random().nextLong());
        System.out.println(new Random().nextBoolean());
    }
}
