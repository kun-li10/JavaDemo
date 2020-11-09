package com.jd.AspectAndAnnotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lk
 * @version 1.0
 * @date 2020/10/10 23:12
 */
public class AspectTest {
  public static void main(String[] args) {

    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext.xml");
    AspectController bean = context.getBean(AspectController.class);
    bean.saveLog("开始!");
  }
}
