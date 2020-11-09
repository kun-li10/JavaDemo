package com.jd.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 数据流 处理基本数据类型,存储和获取顺序必须一致
 * @author lk
 * @version 1.0
 * @date 2020/10/9 13:05
 */
public class DataOutPutStreamTest {
  public static void main(String[] args) {
    FileInputStream fileInputStream=null;
    FileOutputStream fileOutputStream = null;
    DataOutputStream  dataOutputStream= null;
    DataInputStream dataInputStream=null;
    try {
      //写入
      fileOutputStream=new FileOutputStream("abc.txt");
      dataOutputStream=new DataOutputStream(fileOutputStream);
      dataOutputStream.writeBoolean(true);
      dataOutputStream.writeInt(1000);
      dataOutputStream.writeUTF("这是DataOutPutStream!");
      dataOutputStream.writeDouble(3.15);
      //读取
      fileInputStream=new FileInputStream("abc.txt");
      dataInputStream=new DataInputStream(fileInputStream);
      System.out.println(dataInputStream.readBoolean());
      System.out.println(dataInputStream.readInt());
      System.out.println(dataInputStream.readUTF());
      System.out.println(dataInputStream.readDouble());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        fileInputStream.close();
        fileOutputStream.close();
        dataInputStream.close();
        dataOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
