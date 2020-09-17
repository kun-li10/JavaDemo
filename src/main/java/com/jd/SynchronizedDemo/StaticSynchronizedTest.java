package com.jd.SynchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/20 21:09
 */
public class StaticSynchronizedTest {

	private static Object object = new Object();

	public void demo1() {
		synchronized (object) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("demo1..........");
		}
	}

	public  void demo2() {
		synchronized(object) {
			System.out.println("demo2..........");
		}
	}
}

class Test {
	public static void main(String[] args) {
		StaticSynchronizedTest synchronizedTest = new StaticSynchronizedTest();
		new Thread(() -> {
			synchronizedTest.demo1();
		}).start();

		new Thread(() -> {
			synchronizedTest.demo2();
		}).start();

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
