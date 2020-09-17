package com.jd.DesignModel;


/**
 * 策略模式
 *
 * @Author lk
 * @Date 2020/3/20 20:18
 * @Version 1.0
 */
public class Straregy {

    public static void main(String[] args) {
        LogContext context = new LogContext();
        context.Log("记录日志!");
        context.Log("再次记录日志!");
    }

}


interface LogStrategy {
    void log(String msg);
}

class LogContext {
    void Log(String msg) {
        LogStrategy strategy = new DBLog();                 //使用不同的策略
        try {
            strategy.log(msg);
        } catch (Exception e) {
            strategy = new FileLog();
            strategy.log(msg);
        }
    }
}

class DBLog implements LogStrategy {
    @Override
    public void log(String msg) {
        if (msg != null && msg.length() > 5) {
            int a = 5 / 0;   //故意报错
        }
        System.out.println("把" + msg + " 记录到数据库!");
    }
}

class FileLog implements LogStrategy {
    @Override
    public void log(String msg) {
        System.out.println("把" + msg + "记录到文件!");
    }
}


