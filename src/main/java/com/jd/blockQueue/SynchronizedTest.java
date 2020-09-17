package com.jd.blockQueue;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/15 10:45
 */
public class SynchronizedTest {
	public static void main(String[] args) {
		SynchronizedTest test = new SynchronizedTest();
		char[] num = "123456".toCharArray();
		char[] eng = "ABCDEF".toCharArray();
		new Thread(()->{
			try {
				synchronized (test){
					for (int i=0,size=num.length;i<size;i++){
						System.out.print(num[i]);
						test.notifyAll();
						test.wait(); //释放锁
					}
					test.notifyAll();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}).start();

		new Thread(()->{
			try {
				synchronized (test){
					for (int i=0,size=eng.length;i<size;i++){
						System.out.print(eng[i]);
						test.notifyAll();
						test.wait(); //释放锁
					}
					test.notifyAll();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}).start();
	}
}
