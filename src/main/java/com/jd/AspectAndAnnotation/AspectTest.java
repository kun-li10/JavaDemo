package com.jd.AspectAndAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lk
 * @Date 2020/3/29 15:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/aspect")
public class AspectTest {

    @LogAnnotation(value = "开始saveLog")
    public static void saveLog(String log) {
        System.out.println("存储日志成功!");
    }


    @GetMapping("/demo1")
    public void custTest() {
        saveLog("测试aspect功能!");
    }

    public static void main(String[] args) {
        saveLog("开始!");
    }
}
