package com.jd.netAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/16 23:55
 */
public class Server {

  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = new ServerSocket(10086);
    //接收数据
    Socket accept = serverSocket.accept();
    InputStream inputStream = accept.getInputStream();
    DataInputStream dataInputStream = new DataInputStream(inputStream);
    String utf = dataInputStream.readUTF();
    System.out.println(utf);

    //回复消息
    OutputStream outputStream = accept.getOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//    dataOutputStream.writeUTF("Server 回复消息!");
    dataOutputStream.write("收到信息".getBytes());

    //关闭
    serverSocket.close();
    accept.close();
    inputStream.close();
    dataInputStream.close();
    outputStream.close();
    dataOutputStream.close();
  }
}
