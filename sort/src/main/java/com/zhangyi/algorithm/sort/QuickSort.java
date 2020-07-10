package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * @author 张义 johnraychina@163.com
 */
public class QuickSort {
    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a", "e", "x", "a", "m", "p", "l", "e"};
        sort(array);
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {

        if (lo >= hi) { return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        //show("-----------Left sort complete: " + j + "-----------");
        sort(a, j + 1, hi);
        //show("-----------Right sort complete: " + j + "-----------");

    }

    private static int partition(Comparable[] a, int lo, int hi) {

        //a[lo]作为分区的中间元素
        //小于它的准备放左边，大于它的准备放右边
        Comparable mid = a[lo];

        //i是左边元素
        int i = lo;
        //j是右边元素
        int j = hi + 1;

        while (true) {
            while (i < hi) {
                if (a[++i].compareTo(mid) > 0) {
                    break; //从左到右，一直找到比mid大的元素
                }
            }

            while (j > lo) {
                if (a[--j].compareTo(mid) < 0) {
                    break; //从右到左，一直找到比mid小的元素
                }
            }

            if (i >= j) {
                break; //避免死循环
            }

            exchange(a, i, j); // exchange: a[i] 左边比mid大的元素 , a[j] 右边比mid小的元素
            show(a);
        }

        //把mid放到中间
        exchange(a, lo, j);
        show(a);
        return j;
    }
}
