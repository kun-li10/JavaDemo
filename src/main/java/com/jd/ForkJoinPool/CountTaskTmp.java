package com.jd.ForkJoinPool;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool切分合并
 *
 * @author lk
 * @version 1.0
 * @date 2020/8/17 23:37
 */
public class CountTaskTmp extends RecursiveTask<Integer> implements Serializable {

  private static final int THRESHOLD = 2;
  private int start;
  private int end;

  public CountTaskTmp(int start, int end) {
    this.start = start;
    this.end = end;
  }

  /**
   * 实现任务切分计算
   *
   * @return
   */
  @Override
  protected Integer compute() {
    int sum = 0;
    boolean canCompute = (end - start) <= THRESHOLD;
    if (canCompute) {
      for (int i = start; i <= end; i++) {
        sum += i;
      }
    } else {
      int mid = start / 2 + end / 2 + (start % 2 + end % 2) / 2;
      CountTaskTmp leftTask = new CountTaskTmp(start, mid);
      CountTaskTmp rightTask = new CountTaskTmp(mid + 1, end);
      //执行子任务
      leftTask.fork();
      rightTask.fork();
      //等待子任务执行完成,并得到结果
      int left = (int) leftTask.join();
      int right = (int) rightTask.join();
      sum = left + right;
    }
    return sum;
  }

  public static void main(String[] args) {
    //使用ForkJoinPool来执行任务
    ForkJoinPool pool = new ForkJoinPool();
    CountTaskTmp taskTmp = new CountTaskTmp(1, 10000);
    long startTime = System.currentTimeMillis();
    //同步
    Integer invoke = pool.invoke(taskTmp);
    System.out.println(invoke);
    System.out.println("fork耗时:" + (System.currentTimeMillis() - startTime));

    long startThread = System.currentTimeMillis();
    new Thread(() -> {
      for (int i = 1; i < 10001; i++) {
        i += i;
      }
    }).start();
    System.out.println("thread耗时:" + (System.currentTimeMillis() - startThread));


    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Optional<Integer> reduce = list.parallelStream().reduce(Integer::sum);
    if (reduce.isPresent()) {
      System.out.println(reduce.get());
    }

    ForkJoinTask<Integer> future = pool.submit(taskTmp);
    //是否抛出异常
    if (future.isCompletedAbnormally()) {
      System.out.println(future.getException());
    }

  }
}
