package com.jd.stream;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * flatMap 把 input Stream 中的层级结构扁平化，
 * 就是将最底层元素抽出来放到一起，
 * 最终 output 的新 Stream 里面已经没有 List 了，
 * 都是直接的数字。
 *
 * @Author lk
 * @Date 2020/3/2 17:39
 * @Version 1.0
 */
public class StreamFlatMap {

    public static void main(String[] args) throws IOException {
        //SystemIOTest 筛选符合条件的字段 flatMap关键
        String dirPath = "D:\\file";
        String filePath = dirPath + "\\test.txt";
        File file = new File(dirPath);
        if (!file.exists())
            file.mkdir();
        FileWriter fileWriter = new FileWriter(filePath, true);
        for (int i = 0; i < 10; i++) {
            fileWriter.write(i + UUID.randomUUID().toString() + "\r\n");
        }
        //刷新,把kernel中的pagecache刷新到磁盘,尽量当时数据丢失
        fileWriter.flush();
        if (fileWriter != null)
            fileWriter.close();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        List<String> stringList = bufferedReader.lines()
                .flatMap(line -> Stream.of(line.split("-")))
                .filter(work -> work.length() > 11)
                .collect(Collectors.toList());
        System.out.println(stringList);
    }
}
