package com.jd.socket.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/17 21:40
 */
public class PicServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(10010);
    Socket accept = serverSocket.accept();
    InputStream inputStream = accept.getInputStream();
    // 输出到指定的文件
    FileOutputStream fileOutputStream = new FileOutputStream("server.jpg");
    int length = 0;
    while ((length = inputStream.read()) != -1) {
      fileOutputStream.write(length);
    }
    // 切记手动关闭  发出中断的结束
    accept.shutdownInput();

    // 响应客户端
    OutputStream outputStream = accept.getOutputStream();
    outputStream.write("上传图片结束!".getBytes());
    accept.shutdownOutput();

    // 关闭
    inputStream.close();
    serverSocket.close();
    accept.close();
    fileOutputStream.close();
    outputStream.close();
  }
}
