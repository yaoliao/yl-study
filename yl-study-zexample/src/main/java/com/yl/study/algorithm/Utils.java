package com.yl.study.algorithm;

/**
 * @author DELL
 * @date 2018/3/30
 */
public class Utils {


    /**
     * 交换
     *
     * @param a
     * @param i
     * @param j
     * @return
     */
    public static int[] change(int[] a, int i, int j) {
        if (i < 0 || j < 0) {
            throw new RuntimeException("index 不能小于0");
        }
        if (a.length <= i || a.length <= j) {
            throw new RuntimeException("index 不能大于数组长度");
        }
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    /**
     * 利用位运算进行交换
     *
     * @param a
     * @param i
     * @param j
     * @return
     */
    public static int[] bitOperationChange(int[] a, int i, int j) {
        if (i < 0 || j < 0) {
            throw new RuntimeException("index 不能小于0");
        }
        if (a.length <= i || a.length <= j) {
            throw new RuntimeException("index 不能大于数组长度");
        }
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
        return a;
    }

}
