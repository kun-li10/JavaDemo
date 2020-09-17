package com.jd.Lock.Synchronized;

/**
 * synchronized存在脏读测试
 * 避免脏读,同一对象中方法都加上同步,
 * 保证方法的同步执行.锁会是对象,所有加上synchronsized
 * 方法都是按照顺序执行
 *
 * @Author lk
 * @Date 2020/4/11 13:01
 * @Version 1.0
 */
public class SyncFuckRead {
    public static void main(String[] args) throws Exception {
        changeNameAndPassword andPassword = new changeNameAndPassword();
        new Thread(() -> {
            andPassword.change("888", "888");
        }).start();
        Thread.sleep(200);
        System.out.println("当前对象值:" + andPassword.toString());
    }
}

class changeNameAndPassword {
    private String name = "aa";
    private String password = "aa";

    synchronized void change(String name, String password) {
        System.out.println("开始更改值!");
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("更改完成!");
    }

    public synchronized String toString() {
        return "changeNameAndPassword{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
