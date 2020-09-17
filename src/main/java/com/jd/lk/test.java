package com.jd.lk;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lk
 * @Date 2020/3/4 15:40
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("file." + i + "." + i + 10);
        }
        List<String> list1 = list.stream().map(str -> str.split("\\.")[1]).collect(Collectors.toList());
        System.out.println(list);
        Integer max = list.stream()
                .peek(str -> System.out.println(str)) //打印
                .map(string -> Integer.valueOf(string.split("\\.")[1]))
                .collect(Collectors.toList()).stream().reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max);
        List<String> list3 =
                list.stream().filter(string -> string.contains(".110")).map(String::trim).collect(Collectors.toList());
        System.out.println(list3);
        String string = "file.1.3.2";
        System.out.println(string.split("\\.")[1]);

        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 5; i++) {
            atomicInteger.incrementAndGet();
            System.out.println("次数：" + i + "  get值: " + atomicInteger.get());
        }
    }
}

class IOTest {
    public static void main(String[] args) throws IOException {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("D" +
                    ":\\桌面\\product_heap.txt")));
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new FileOutputStream(new File("D:\\桌面\\test.txt")));
            byte[] line = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(line)) != -1) {
                dataOutputStream.write(line, 0, read);
                dataOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


class Demo {

    public static void main(String[] args) {
        String ss = "[1234]";
        String substring = ss.substring(1, ss.length() - 1);
        String[] split = substring.split(",");
        List<String> list = Stream.of(split).map(value -> value.trim()).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list.size());
    }
}
