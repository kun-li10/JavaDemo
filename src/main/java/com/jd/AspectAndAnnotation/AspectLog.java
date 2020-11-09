package com.jd.AspectAndAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author lk
 * @Date 2020/3/29 14:43
 * @Version 1.0
 */
@Aspect
@Component
public class AspectLog {

  /**
   * 通知注解有以下几种类型：
   *
   * @Before:前置通知，在方法执行之前完成
   * @After：后置通知，在方法执行完成之后执行
   * @AfterReturing：返回通知：在返回结果之后运行
   * @AfterThrowing：异常通知：出现异常的时候使用
   * @Around：环绕通知
   *
   * 在方法的参数列表中不要随便添加参数值，会有异常信息
   */

    String result = "切面成功!";

    @Pointcut("@annotation(com.jd.AspectAndAnnotation.LogAnnotation)")
    public void LogPointCut() {
    } //签名

    @Around("LogPointCut()&&args(log)")
    public String saveLogAudit(ProceedingJoinPoint point, String log) {
        System.out.println("获取参数为: " + log);
        //返回方法名
        String methodName = point.getSignature().getName();
        System.out.println("获取类对象:" + point.getTarget());
        Class<?> targetClass = point.getTarget().getClass();
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                if (method.isAnnotationPresent(LogAnnotation.class)) {
                    LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
                    System.out.println("注解参数为: " + annotation.value());
                }
            }
        }
        try {
            point.proceed();//执行目标方法,返回目标方法的值
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    @Before(value = "LogPointCut()")
    public void beforeMethod() {
        System.out.println("先执行方法!");
    }

    @After("@annotation(com.jd.AspectAndAnnotation.LogAnnotation)")
    public void afterMethod() {
        System.out.printf("后执行方法");
    }
}
