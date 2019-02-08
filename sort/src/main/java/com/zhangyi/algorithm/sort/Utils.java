package com.zhangyi.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 */
public class Utils {

    public static <T extends Comparable<T>> boolean compareAndExchange(T[] array, int left, int right) {
        if (array[left].compareTo(array[right]) > 0) {
            exchange(array, left, right);
            return true;
        } else {
            return false;
        }
    }

    public static <T> void exchange(T[] a, int x, int y) {
        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static <T> void show(Object object) {
        System.out.println(JSON.toJSONString(object));
    }
}
