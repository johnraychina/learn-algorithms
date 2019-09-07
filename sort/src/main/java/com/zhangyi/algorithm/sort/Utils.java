package com.zhangyi.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 */
public class Utils {

    /**
     * exchange x with y, if x is bigger than y
     * @param array
     * @param x
     * @param y
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean exchangeIfBigger(T[] array, int x, int y) {
        if (array[x].compareTo(array[y]) > 0) {
            exchange(array, x, y);
            return true;
        } else {
            return false;
        }
    }

    public static <T extends Comparable<T>> boolean less(T[] array, int x, int y) {
        return array[x].compareTo(array[y]) < 0;
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
