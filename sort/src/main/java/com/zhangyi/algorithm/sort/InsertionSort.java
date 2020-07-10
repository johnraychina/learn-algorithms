package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.exchangeIfBigger;
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
 * @author 张义 johnraychina@163.com
 */
public class InsertionSort {

    public static <T extends Comparable> void sort(T[] array) {

        show(array);

        int N = array.length -1;
        //get first element from right side, compare with left sorted side and insert
        for (int i = 0; i < N; i++) {

            //sorted left side: [0...i],  right side: [i+1 ... N]
            //elementToInsert : array[i+1];

            //exchange elementToInsert all the way to appropriate position in sorted left side [0 ... i]
            for (int j = i; j >= 0; j--) {
                boolean exchanged = exchangeIfBigger(array, j, j + 1);
                if (exchanged) {
                    show(array);
                } else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a", "e", "x", "a", "m", "p", "l", "e"};
        sort(array);
    }
}
