package com.jd.io.System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * 标准输入/标准输出
 * 控制台输入/输出--->标准
 * @author lk
 * @version 1.0
 * @date 2020/10/9 15:46
 */
public class SystemIOTest {
  public static void main(String[] args) {

    String result = "";
    try(InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
      while (!result.equals("exit")) {
        result = bufferedReader.readLine();
        bufferedWriter.write(result+"\n");
        bufferedWriter.flush();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
}
