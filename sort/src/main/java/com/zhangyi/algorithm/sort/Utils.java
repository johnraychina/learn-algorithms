package com.zhangyi.algorithm.sort;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class Utils {

    /**
     * exchange x with y, if x is bigger than y
     *
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
        if (x == y) { return false; }
        return array[x].compareTo(array[y]) < 0;
    }

    public static <T extends Comparable<T>> boolean less(T x, T y) {
        if (x == y) { return false; }
        return x.compareTo(y) < 0;
    }

    public static <T> void exchange(T[] a, int x, int y) {
        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static <T> void show(Object object) {
        System.out.println(object.toString());
    }

    public static <T> void show(T[] array) {
        String str = Stream.of(array).map(Object::toString).collect(Collectors.joining(",", "[", "]"));
        System.out.println(str);
    }


    public static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
