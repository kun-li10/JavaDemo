package com.jd.avg;

/**
 * 防止求和整数溢出
 * 防止avg有偏差 3/2 +5/2
 *
 * @author lk
 * @version 1.0
 * @date 2020/8/17 12:31
 */
public class AvgTest {

	public static void main(String[] args) {
		int a = 3;
		int b = 5;
		//商加上取余均值
		int avg = a / 2 + b / 2 + (a % 2 + b % 2) / 2;
		System.out.println("取余:" + avg);

		//通过位运算
		int avg2 = (a >> 1) + (b >> 1) + (((a & 2) + (b & 2)) >> 1);
		System.out.println("位运算:" + avg2);
	}
}
