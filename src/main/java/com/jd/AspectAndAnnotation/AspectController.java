package com.jd.AspectAndAnnotation;

import org.springframework.stereotype.Component;

/**
 * @Author lk
 * @Date 2020/3/29 15:03
 * @Version 1.0
 */
@Component
public class AspectController {

    @LogAnnotation(value = "开始saveLog")
    public void saveLog(String log) {
        System.out.println("存储日志成功!");
    }

    public void custTest() {
        saveLog("测试aspect功能!");
    }
}
