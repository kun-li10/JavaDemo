package com.jd.algorithmFunct;

/**
 * @Author lk
 * @Date 2020/4/26 16:24
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
//        int[] a = {1, 1, 4, 4, 4, 5, 5,};
//        for (int i = 1, length = a.length; i < length; i++) {
//            a[0] = a[0] ^ a[i];
//        }
//        System.out.println(a[0]);
        int[] arr = {1, 4, 4, 1, 6, 6, 6, 7, 8, 8, 8, 7};
        findOnce(arr, arr.length);
    }

    static void findOnce(int[] data, int length) {
        int num1 = 0;
        int num2 = 0;
        if (length < 2)
            return;
        int resExclusiveOR = data[0];
        for (int i = 1; i < length; i++) {
            resExclusiveOR ^= data[i];
        }
        int indexOf1 = findFirstBitIs1(resExclusiveOR);
        for (int i = 0; i < length; i++) {
            if (isBit1(data[i], indexOf1)) {
                num1 = num1 ^ data[i];
            } else {
                num2 = num2 ^ data[i];
            }
        }
        System.out.println(num1 + " " + num2);
    }

    static int findFirstBitIs1(int r) {
        int indexBit = 0;
        while ((r & 1) == 0 && (indexBit < 32)) {
            r = r >> 1;
            indexBit++;
        }
        return indexBit;
    }

    static boolean isBit1(int num, int index) {
        if (((num >> index) & 1) == 1)
            return true;
        else
            return false;
    }
}

//判断某一个数第i位是0还是1
class test {
    public static void main(String[] args) {
        int number = -190; //0000 1001
        int i = 3;      //0000 1000
        System.out.println(getBit(number, i));
        System.out.println("数值二进制有1的个数:" + getCount(number));
        System.out.println("getCount2:" + getCount2(-190));
        System.out.println("getCount1:" + getCount(-190));
    }

    private static boolean getBit(int num, int b) {
        return ((num & (1 << b)) == 0);
    }

    //二进制中有1的个数
    private static int getCount(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    private static int getCount2(int num) {
        int count = 0;
        for (; num > 0; count++) {
            num &= (num - 1);
        }
        return count;
    }
}

class Number {
    public static void main(String[] args) {
        int num = 9;
        System.out.println("右移:" + getCu(num));
        System.out.println("左移:" + getCu2(num));
    }

    //右移
    private static int getCu(int number) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((number & 1) == 1)
                count++;
            number >>= 1;
        }
        return count;
    }

    //左移
    private static int getCu2(int number) {
        int count = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            if ((number & flag) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }
}
