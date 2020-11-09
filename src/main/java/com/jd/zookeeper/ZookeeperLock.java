package com.jd.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * zookeeper有session的概念,所以没有连接池的概念 zookeeper分布式共享锁 @Author likun @Date 2019/11/6 9:46 @Version 1.0
 */
public class ZookeeperLock implements Watcher {

  private static Logger LOG = LogManager.getLogger(ZookeeperLock.class.getName());

  private String ROOT_LOCK_PATH = "/locks";
  private String PRE_LOCK_NAME = "lock_";
  private static ZookeeperLock lock;
  private static ZooKeeper zkClient = null;
  private CountDownLatch latch = new CountDownLatch(1);

  public static ZookeeperLock getInstance() {
    if (null == lock) {
      lock = new ZookeeperLock();
    }
    return lock;
  }

  /**
   * 获取锁失败后重试9次
   *
   * @return
   */
  public boolean getZkLock() throws InterruptedException {
    for (int i = 1, count = 9; i < count; i++) {
      getZkClient();
      String lock = getLock();
      // 关闭链接
      releaseConnection();
      if (!StringUtils.isEmpty(lock)) {
        return true;
      }
      TimeUnit.SECONDS.sleep(i * 10);
    }
    return false;
  }

  /**
   * 获取锁：实际上是创建线程目录，并判断线程目录序号是否最小
   *
   * @return
   */
  public String getLock() {
    try {
      // 判断根路径是否存在
      Stat stat = zkClient.exists(ROOT_LOCK_PATH, false);
      if (stat == null) {
        zkClient.create(ROOT_LOCK_PATH, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      }
      String lockPath =
          zkClient.create(
              ROOT_LOCK_PATH + "/" + PRE_LOCK_NAME,
              Thread.currentThread().getName().getBytes(),
              Ids.OPEN_ACL_UNSAFE,
              CreateMode.EPHEMERAL_SEQUENTIAL);
      LOG.info(Thread.currentThread().getName() + "create lock path:{}" + lockPath);
      tryLock(lockPath);
      return lockPath;
    } catch (Exception e) {
      LOG.error("获取锁失败:{}", e.getMessage());
    }
    return null;
  }

  private boolean tryLock(String lockPath) throws KeeperException, InterruptedException {
    // 获取ROOT_LOCK_PATH下所有的子节点，并按照节点序号排序
    List<String> lockPaths = zkClient.getChildren(ROOT_LOCK_PATH, false);
    Collections.sort(lockPaths);
    LOG.info("all chileNode:{}", lockPaths.toString());
    int index = lockPaths.indexOf(lockPath.substring(ROOT_LOCK_PATH.length() + 1));
    if (index == 0) {
      LOG.info(Thread.currentThread().getName() + "get lock,lock path:{}" + lockPath);
      return true;
    } else {
      // 创建Watcher，监控lockPath的前一个节点
      Watcher watcher =
          new Watcher() {
            @Override
            public void process(WatchedEvent event) {
              // 创建的锁目录只有删除事件
              LOG.info("Received delete event, node path is:{}" + event.getPath());
              synchronized (this) {
                notifyAll();
              }
            }
          };
      String preLockPath = lockPaths.get(index - 1);
      // 查询前一个目录是否存在，并且注册目录事件监听器，监听一次事件后即删除
      Stat state = zkClient.exists(ROOT_LOCK_PATH + "/" + preLockPath, watcher);
      // 返回值为目录详细信息
      if (state == null) {
        return tryLock(lockPath);
      } else {
        LOG.info(Thread.currentThread().getName() + ":wait for:{}" + preLockPath);
        synchronized (watcher) {
          // 等待目录删除事件唤醒 设置自动唤醒超时
          watcher.wait(2000);
        }
        return tryLock(lockPath);
      }
    }
  }

  /**
   * 释放锁：实际上是删除当前线程目录
   *
   * @param lockPath
   */
  public void releaseLock(String lockPath) {
    try {
      zkClient.delete(lockPath, -1);
      LOG.info("Release lock,lock path is" + lockPath);
    } catch (InterruptedException e) {
      LOG.error("释放锁错误:{}", e.getMessage());
    } catch (KeeperException e) {
      LOG.error("释放锁错误:{}", e.getMessage());
    } catch (Exception e) {
      LOG.error("删除错误:{}", e.getMessage());
    }
  }

  /** 关闭ZK连接 */
  public void releaseConnection() {
    if (null != zkClient) {
      try {
        zkClient.close();
      } catch (InterruptedException e) {
        LOG.error("release connection error ," + e.getMessage(), e);
      }
    }
  }

  /**
   * 获取数据异步回调
   *
   * @return
   */
  public String getDataPathAsync() {
    ZooKeeper zkClient = getZkClient();
    zkClient.getData(
        "/lock",
        false,
        new AsyncCallback.DataCallback() {
          @Override
          public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            System.out.println("设置的回调标识: " + o.toString());
            System.out.println("获取的数据: " + new String(bytes));
          }
        },
        "abc");
    return null;
  }
  /**
   * 获取zookeeper链接
   *
   * @return
   */
  public ZooKeeper getZkClient() {
    // 创建zookeeper的链接
    try {
      zkClient = new ZooKeeper("111.231.107.218:2181,111.231.107.218:2182", 10, this);
      // 等待创建完成
      latch.await();
    } catch (IOException e) {
      LOG.error("锁创建zookeeper客户端失败:{}", e.getMessage());
    } catch (InterruptedException e) {
      LOG.error("锁创建zookeeper客户端失败:{}", e.getMessage());
    }
    return zkClient;
  }

  @Override
  public void process(WatchedEvent event) {
    Event.KeeperState state = event.getState();
    Event.EventType type = event.getType();
    switch (state) {
      case SyncConnected:
        System.out.println("end");
        latch.countDown();
        break;
      case Unknown:
        break;
      case Expired:
        break;
      case AuthFailed:
        break;
      case Disconnected:
        break;
      case Closed:
        break;
      case ConnectedReadOnly:
        break;
      case SaslAuthenticated:
        break;
      case NoSyncConnected:
        break;
      default:
        break;
    }
    switch (type) {
      case None:
        break;
      case NodeDeleted:
        break;
      case NodeCreated:
        break;
      case NodeDataChanged:
        break;
      case DataWatchRemoved:
        break;
      case ChildWatchRemoved:
        break;
      case NodeChildrenChanged:
        break;
      case PersistentWatchRemoved:
        break;
      default:
        break;
    }
    //    if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
    //      // 创建完成，唤醒等待线程
    //      latch.countDown();
    //    }
  }
}
