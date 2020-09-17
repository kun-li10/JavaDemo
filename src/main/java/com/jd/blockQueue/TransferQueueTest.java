package com.jd.blockQueue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 交换队列,交替打印
 * @author lk
 * @version 1.0
 * @date 2020/8/15 10:19
 */
public class TransferQueueTest {
 static  LinkedTransferQueue transferQueue=	new LinkedTransferQueue();

	public static void main(String[] args){
		char[] num = "123456".toCharArray();
		char[] enl = "ABCDEF".toCharArray();
		new Thread(()->{
			try {
				for (int i = 0, size = num.length; i < size; i++) {
					transferQueue.transfer(num[i]);
					System.out.print(transferQueue.take());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}).start();

		new Thread(()->{
			try{
				for (int j =0,size=enl.length;j<size;j++){
					System.out.print(transferQueue.take());
					transferQueue.transfer(enl[j]);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}).start();
	}
}
