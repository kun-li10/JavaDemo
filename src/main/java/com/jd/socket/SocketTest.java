package com.jd.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO模型,主线程阻塞等待,有客户端accept后创建
 * 另外一个线程进行处理客户端
 *
 * @Author lk
 * @Date 2020/4/9 20:30
 * @Version 1.0
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8090);
        System.out.println("socket1:new ServerSocket(80)");
        while (true) {
            Socket accept = socket.accept();
            System.out.println("socket2:" + accept.getPort());
            new Thread(() -> {
                try {
                    InputStream stream = accept.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    while (true) {
                        System.out.println(reader.lines());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
