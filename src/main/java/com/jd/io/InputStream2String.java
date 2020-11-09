package com.jd.io;


import cn.hutool.core.io.IoUtil;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * InputStream -> String
 * @author lk
 * @version 1.0
 * @date 2020/10/9 11:06
 */
public class InputStream2String {

  public static void main(String[] args) throws IOException {

    System.out.println("-----------------Stream Api -----------------------");
    FileInputStream fileInputStream = new FileInputStream("src.txt");
    String collect =
        new BufferedReader(new InputStreamReader(fileInputStream)).lines().collect(Collectors.joining("\n"));
    System.out.println(collect);

    System.out.println("----------------Commons-SystemIOTest------------------------");
    FileInputStream inputStream = new FileInputStream("dst.txt");
    String read = IoUtil.read(inputStream, "UTF-8");
    System.out.println(read);

    System.out.println("--------------Guava-----------------------------");
//    StringInputStream stringInputStream = new StringInputStream("aaaaaaaaaaaaaa");
//    try {
//      String string = CharStreams.toString(new InputStreamReader(stringInputStream, "UTF-8"));
//      System.out.println(string);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    System.out.println("------------------ByteArrayOutPutStream-----------------");
//    BufferedInputStream bufferedInputStream = new BufferedInputStream(new StringInputStream(
//        "ByteArrayOutPutStream"));
//    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    int result = bufferedInputStream.read();
//   while(result!=-1){
//     byteArrayOutputStream.write((byte)result);
//     result=bufferedInputStream.read();
//   }
//    String string = byteArrayOutputStream.toString("UTF-8");
//    System.out.println(string);

    System.out.println("----------------IoUtils-------------------");
//    BufferedInputStream putStream = new BufferedInputStream(new StringInputStream(
//        "IoUtils"));
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    IoUtil.copy(putStream,outputStream);
//    String ioString = outputStream.toString("UTF-8");
//    System.out.println(ioString);

    System.out.println("-----------------String---------------");
    FileInputStream fileInputStream2 = new FileInputStream("src.txt");
    String result2 = new String(ByteStreams.toByteArray(fileInputStream2), "UTF-8");
    System.out.println(result2);


  }
}
