package com.jd.WordToPDF;


import com.spire.doc.Document;
import com.spire.doc.FileFormat;

/**
 * @Author lk
 * @Date 2020/4/1 15:05
 * @Version 1.0
 */
public class wordTranformPDF {
    public static void main(String[] args) throws Exception {
        Document document = new Document();

        document.loadFromFile("D:\\test.docx");
        document.saveToFile("D:\\桌面\\main", FileFormat.PDF);
    }
}
