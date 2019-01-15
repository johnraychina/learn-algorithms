package com.zhangyi.algorithm.sort;

import com.alibaba.fastjson.JSON;

import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * 插入排序
 *
 * 类似整理桥牌
 *
 * 与选择排序一样，左边是有序的，和选择排序不同的是：
 * 1、插入排序在完成排序前，左边的只是相对有序，最终位置还不确定；
 * 2、初始顺序对排序时间影响巨大；
 *
 * 平均比较次数：N^2/4
 * 平均交换次数：N^2/4
 *
 * 最好的情况，比较次数：N-1
 * 最好的情况，交换次数：0
 *
 * 最坏的情况，比较次数：N^2/2
 * 最坏的情况，交换次数：N^2/2
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Insertion {
    public static <T extends Comparable> void sort(T[] array) {

        show(array);

        int N = array.length;
        //get first element from right side, compare with left sorted side and insert
        for (int i = 0; i < N; i++) {

            //sorted left side: 0...i,  right side: i+1 ... N
            for (int j = i; j >= 0; j--) {

                if (i + 1 < N && justBefore(array, i + 1, j)) {
                    T elementToInsert = array[i + 1];
                    moveToRight(array, j, i);
                    array[j] = elementToInsert;

                    show(array);
                }
            }
        }
    }

    private static <T extends Comparable> boolean justBefore(T[] array, int index, int sortedIndex) {
        if (sortedIndex > 0) {
            return array[index].compareTo(array[sortedIndex - 1]) >= 0
                && array[index].compareTo(array[sortedIndex]) < 0;
        } else {
            return array[index].compareTo(array[sortedIndex]) < 0;
        }
    }

    /**
     * j...i => [j:elementToInsert] j+1...i+1
     *
     * @param array
     * @param start
     * @param end
     * @param <T>
     */
    private static <T> void moveToRight(T[] array, int start, int end) {
        for (int k = end; k >= start; k--) {
            array[k + 1] = array[k];
        }
    }

    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a", "e", "x", "a", "m", "p", "l", "e"};
        sort(array);
    }
}
