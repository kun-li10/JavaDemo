package com.jd.execption;

/**
 * @Author lk
 * @Date 2020/4/21 17:19
 * @Version 1.0
 */
public class MyException extends RuntimeException {
    private int id;

    public MyException(String message, int id) {
        super(message);
        this.id = id;
    }

    public MyException(Throwable cause, int id) {
        super(cause);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Demo {

    public void demo(int num) throws MyException {
        if (num < 0)
            throw new MyException("值为负数!", 1);
    }

    public void demo2() {
        try {
            demo(-1);
        } catch (MyException e) {
            System.out.println("登记出错!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Demo().demo2();
    }
}
