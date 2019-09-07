package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.exchangeIfBigger;
import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * 希尔排序
 * <p>
 * 选择排序和插入排序一次对比和交换位置只能减少一次乱序数，对于非常乱的数据而言，效果不好。
 * 我们把距离为delta的数据作为一组，每组组内数据做选择排序，然后再缩小h -> 一直到1(2个相邻元素)即可。
 *
 * 这样，组内排序时，一次对比和交换位置就可能减少h个。
 *
 * 对于非常乱的数据非常有用。
 * </p>
 *
 * @author 张义 johnraychina@163.com
 */
public class Shell {

    public static <T extends Comparable<T>> void sort(T[] array) {

        int n = array.length;
        int h = 1;
        while (h < n / 3) { h = 3 * h + 1; } //1, 4, 13, 40 ...

        while (h >= 1) {

            for (int i = h; i < n; i++) {

                for (int j = i; j >= h && array[j].compareTo(array[j - h]) < 0; j -= h) {
                    exchange(array, j, j - h);
                    show(array);
                }
            }

            h = h / 3;

        }
    }

    public static <T extends Comparable> void sort2(T[] array) {

        int n = array.length;

        int delta = n / 2;  //1, 2, 4, 8 ... ...

        while (delta >= 1) {
            show("delta:" + delta);

            //for i=[0:delta)
            //[i, i+delta, i+2delta)

            for (int i = 0; i < n; i++) {

                for (int j = i; j + delta < n && array[j].compareTo(array[j+delta]) > 0; j += delta) {
                    exchangeIfBigger(array, j, j + delta);
                    show(array);
                }
            }

            delta = delta / 2;
        }
    }

    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a", "e", "x", "a", "m", "p", "l", "e"};
        sort2(array);
    }

}
