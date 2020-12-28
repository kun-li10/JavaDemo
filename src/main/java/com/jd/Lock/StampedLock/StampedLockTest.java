package com.jd.Lock.StampedLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock的使用
 *
 * @author lk
 * @version 1.0
 * @date 2020/12/9 8:50
 */
public class StampedLockTest {
  /**
   * 共享属性
   */
  Map<String, String> map = new HashMap<String, String>();
  StampedLock stampedLock = new StampedLock();

  public void put(String k, String v) {
    long writeStamp = stampedLock.writeLock();
    try {
      map.put(k, v);
    } finally {
      stampedLock.unlockWrite(writeStamp);
    }
  }

  public String get(String k) {
    long readStamp = stampedLock.readLock();
    try {
      return map.get(k);
    } finally {
      stampedLock.unlockRead(readStamp);
    }
  }

  /**
   * 乐观读模式
   *
   * @return
   */
  public String optimismRead(String key) {
    //乐观读
    //对上一步获取的结果进行验证
    //如果验证失败，则此时可能其他线程加了写锁，那么此时线程通过加读锁进入阻塞状态直到获取到读锁
    //如果验证成功，不进行任何加锁操作直接返回共享数据，这样的话就实现了无锁读的操作，提高了读访问性能。
    long stamp = stampedLock.tryOptimisticRead();
    if (stampedLock.validate(stamp)) {
      stamp = stampedLock.readLock();
      try {
        return map.get(key);
      } finally {
        stampedLock.unlockRead(stamp);
      }
    }
    return map.get(key);
  }
}
