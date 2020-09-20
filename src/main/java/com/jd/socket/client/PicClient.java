package com.jd.socket.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 服务端上传图片
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/17 21:34
 */
public class PicClient {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 10010);
    FileInputStream fileInputStream = new FileInputStream("img.jpg");
    OutputStream outputStream = socket.getOutputStream();
    int tmp = 0;
    while ((tmp = fileInputStream.read()) != -1) {
      outputStream.write(tmp);
    }
    socket.shutdownOutput();

    // 接收服务端响应
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int read = inputStream.read(bytes);
    System.out.println(new String(bytes, 0, read));
    socket.shutdownInput();

    // 关闭
    socket.close();
    fileInputStream.close();
    outputStream.close();
    inputStream.close();
  }
}
