package com.jd.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 自定义线程池,
 * 自定义任务队列
 * 自定义线程工厂
 * 自定义拒绝策略
 *
 * @Author lk
 * @Date 2020/3/8 16:23
 * @Version 1.0
 */
public class CreateThreadPool {

	private static final CountDownLatch latch = new CountDownLatch(7);

	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MICROSECONDS,
				new ArrayBlockingQueue<>(5), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				System.out.println("开始创建线程:" + r.hashCode());
				Thread thread = new Thread(r, "threadName-" + r.hashCode());
				return thread;
			}
		}, new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("任务队列数:" + executor.getQueue().size() + " 总任务:" + executor.getTaskCount());
				//可以通过判断队列中的任务数量进行重复提交
				//try 3 times 进行任务提交
				//或者把任务失败的失败的持久化到kafka redis mysql等进行后续的处理
			}
		}) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("任务: " + ((ThreadTask) r).getTaskName() + "开始执行任务!");
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("任务:" + ((ThreadTask) r).getTaskName() + "结束执行!");
			}

			@Override
			protected void terminated() {
				System.out.println("线程池退出!");
			}
		};
		for (int i = 0; i < 6; i++) {
			poolExecutor.execute(new ThreadTask("task-" + i));
		}
		try {
			latch.await();
			System.out.println("任务执行完毕!");
			poolExecutor.shutdown();
			poolExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class ThreadTask implements Runnable {
		private String taskName;

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public ThreadTask(String name) {
			this.setTaskName(name);
		}

		public void run() {
			try {
				//让线程阻塞，使后续任务进入缓存队列
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//            System.out.println("TaskName" + this.getTaskName() + "---ThreadName:" + Thread.currentThread().getName());
			latch.countDown();
		}
	}
}
