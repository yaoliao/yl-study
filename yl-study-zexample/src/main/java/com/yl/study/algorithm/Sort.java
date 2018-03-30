package com.yl.study.algorithm;

import java.util.Arrays;

/**
 * @author DELL
 * @date 2018/3/30
 */
public class Sort {


    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(int[] a) {

        int len = a.length;

        for (int i = 0; i < len; i++) {
            int min = i;

            for (int j = i; j < len; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            Utils.change(a, i, min);
        }
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] a) {

        int len = a.length;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                Utils.change(a, j, j - 1);
            }
        }
    }


    /**
     * 希尔排序
     *
     * @param a
     */
    public static void shellSort(int[] a) {

        int len = a.length;
        int h = 1;
        if (h < len / 3) {
            //选取合适的递增数列可以提高算法性能   h = 1/2(3^k - 1)  1, 4, 13, 40, 121, 364, 1093, ...
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && (a[j] < a[j - h]); j -= h) {
                    Utils.bitOperationChange(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * 归并排序
     *
     * @param a
     * @param start 数组下标
     * @param end   数组下标
     */
    public static void mergeSort(int[] a, int start, int end) {

        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;

        //排序左半边
        mergeSort(a, start, mid);
        //排序右半边
        mergeSort(a, mid + 1, end);

        merge(a, start, mid, end);
    }

    /**
     * 原地归并的抽象方法
     *
     * @param a
     * @param start
     * @param mind
     * @param end
     */
    private static void merge(int[] a, int start, int mind, int end) {

        int len = a.length;
        int i = start;
        int j = mind + 1;
        int[] b = new int[len];
        for (int k = start; k <= end; k++) {
            b[k] = a[k];
        }

        for (int k = start; k <= end; k++) {
            if (i > mind) {
                a[k] = b[j++];
            } else if (j > end) {
                a[k] = b[i++];
            } else if (b[i] > b[j]) {
                a[k] = b[j++];
            } else {
                a[k] = b[i++];
            }
        }
    }


    /**
     * 快速排序
     *
     * @param a
     * @param start
     * @param end
     */
    public static void quickSort(int[] a, int start, int end) {

        if (start >= end) return;
//        int len = a.length;

        int partition = partition(a, start, end);
        quickSort(a, start, partition);
        quickSort(a, partition + 1, end);

    }

    private static int partition(int[] a, int start, int end) {

        int pivot = a[start];
        while (start < end) {
            while (start < end && pivot <= a[end]) {
                end--;
            }
            a[start] = a[end];
            while (start < end && pivot >= a[start]) {
                start++;
            }
            a[end] = a[start];
        }
        a[start] = pivot;
        return start;
    }


    public static void main(String[] args) {
        int[] array = new int[]{23, 45, 12, 4, 1, 39, 602, 56, 46, 100, 34, 566, 2, 144, 28, 134};
//        selectSort(array);
//        insertionSort(array);
//        shellSort(array);
//        mergeSort(array, 0, array.length - 1);
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }

}
