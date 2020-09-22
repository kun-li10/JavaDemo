package com.jd.socket.client;

import com.jd.socket.model.User;
import sun.misc.Unsafe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/17 22:01
 */
public class LoginClient {

  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 10011);
    OutputStream outputStream = socket.getOutputStream();
    User user = User.builder().address("China").password("0000").username("likun").build();
    ObjectOutputStream stream = new ObjectOutputStream(outputStream);
    stream.writeObject(user);
    // 通知结束传输
    socket.shutdownOutput();

    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
    String utf = dataInputStream.readUTF();
    System.out.println(utf);
    socket.shutdownInput();

    // 关闭
    socket.close();
    outputStream.close();
    stream.close();
    dataInputStream.close();
  }
}
