package com.jd.jvm.classload;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/9 12:38
 */

public class T001_ClassLoadingProcedure {
    public static void main(String[] args) {
        System.out.println(T.count);
    }
}

class T {
    public static T t = new T(); // null
    public static int count = 2; //0

    //private int m = 8;

    private T() {
        count ++;
        //System.out.println("--" + count);
    }
}
