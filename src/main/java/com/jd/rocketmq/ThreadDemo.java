package com.jd.rocketmq;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/23 22:38
 */
public class ThreadDemo {

	public static void main(String[] args) {
		System.out.println("main start");
		new Thread(()->{
			while (true){
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread");
			}
		}).start();

		System.out.println("main end");
	}
}
