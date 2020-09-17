package com.jd.Random;

import com.jd.util.UUIDUtil;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 *
 * @author lk
 * @version 1.0
 * @date 2020/8/13 9:22
 */
public class BlockQueue<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	/**
	 * 容量
	 */
	int MAX = 20;
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	void put(T value) {
		try {
			lock.lock();
			while (lists.size() == MAX) {
				System.out.println("队列已满!");
				producer.await();
			}
			lists.add(value);
			//唤醒阻塞
			consumer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	T getValue() {
		T result = null;
		try {
			lock.lock();
			while (lists.size() <= 0) {
				System.out.println("队列已空!");
				consumer.await();
			}
			result = lists.removeFirst();
			//唤醒producer
			producer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return result;
	}

	public static void main(String[] args) {
		BlockQueue<String> queue = new BlockQueue<>();
		queue.put(UUID.randomUUID().toString());

	}
}
