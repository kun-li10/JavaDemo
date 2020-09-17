package com.jd.netAddress;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/16 23:52
 */
public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 10086);

    //发送数据
    OutputStream outputStream = socket.getOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
    dataOutputStream.writeUTF("Hello,ServerSocket");

    //接收数据
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int length = inputStream.read(bytes);
    System.out.println("Client接收到数据:" + new String(bytes, 0, length));

    //关闭
    socket.close();
    outputStream.close();
    dataOutputStream.close();
    inputStream.close();
  }
}
