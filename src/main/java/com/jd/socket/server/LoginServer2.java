package com.jd.socket.server;

import com.jd.socket.model.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server while循环不结束
 * @author lk
 * @version 1.0
 * @date 2020/9/17 22:04
 */
public class LoginServer2 {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ServerSocket socket = new ServerSocket(10011);
    while (true) {
      Socket accept = socket.accept();
      InputStream inputStream = accept.getInputStream();
      ObjectInputStream stream = new ObjectInputStream(inputStream);
      User user = (User) stream.readObject();
      System.out.println(user.toString());
      String response = "";
      if (user.getPassword().equals("000")) {
        response = "登陆成功!";
      } else {
        response = "登录失败!";
      }
      // 通知结束
      accept.shutdownInput();
      // 返回登录消息
      OutputStream outputStream = accept.getOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
      dataOutputStream.writeUTF(response);
      accept.shutdownOutput();
      // 关闭
      accept.close();
      inputStream.close();
      stream.close();
      outputStream.close();
      dataOutputStream.close();
    }
    // 关闭
    //    socket.close();
  }
}
