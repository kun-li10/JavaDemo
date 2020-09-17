package com.jd.thread;

import javax.swing.*;
import java.awt.*;

/**
 * 线程加入
 * 验证join的功能
 * 例如: theadA正在run -> threadB.join() = threadA暂停等待线程threadB执行完再开始执行
 *
 * @Author lk
 * @Date 2020/3/3 13:33
 * @Version 1.0
 */
public class ThreadJoin extends JFrame {
    private Thread threadA;
    private Thread threadB;
    final JProgressBar progressBar1 = new JProgressBar();
    final JProgressBar progressBar2 = new JProgressBar();
    int count = 0;

    public static void main(String[] args) {
        init(new ThreadJoin(), 100, 100);
    }

    public ThreadJoin() {
        super();
        getContentPane().add(progressBar1, BorderLayout.NORTH);
        getContentPane().add(progressBar2, BorderLayout.SOUTH);
        progressBar1.setStringPainted(true);
        progressBar2.setStringPainted(true);
        threadA = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    progressBar1.setValue(++count);
                    try {
                        Thread.sleep(100);
                        threadB.join();      //线程A暂停,等待线程B执行完再开始执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 100) {
                        break;
                    }
                }
            }
        });
        threadA.start();
        threadB = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    progressBar2.setValue(++count);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 100) {
                        break;
                    }
                }
            }
        });
        threadB.start();
    }

    static void init(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
