package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * 选择排序
 * selection sort.
 *
 * 固定N^2次比较
 * 固定N次交换
 * 额外空间：1个temp
 */
public class Selection {


    public static <T extends Comparable<T>> void sort(T[] array) {

        show(array);

        //first, pick the smallest one and exchange to the left sorted part
        //second, pick the second smallest and exchange to the left sorted part
        //...
        int N = array.length;
        for (int i = 0; i < N; i++) {

            int indexOfMin = i;
            //compare array[i] with all the right side elements array[j]
            for (int j = indexOfMin; j < N; j++) {
                if (array[indexOfMin].compareTo(array[j]) > 0) {
                    indexOfMin = j;
                }
            }
            exchange(array, i, indexOfMin);

            show(array);
        }
    }


    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a"};
        sort(array);
    }
}
