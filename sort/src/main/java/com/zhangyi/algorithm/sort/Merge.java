package com.zhangyi.algorithm.sort;

import java.util.Arrays;

import static com.zhangyi.algorithm.sort.Utils.compareAndExchange;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * 归并排序
 * <p>
 * 采用分而治之的思想，先拆分为2个子数组，分别排序，然后再将2个有序子数组合并为一个有序数组；
 * 用递归的办法一直拆分到最后子数组只有2个或1个元素，简单比较合交换即可。
 * </p>
 *
 * 问题：递归太多层影响执行速度
 * 优化：拆分子数组到一定粒度就不再拆分（比如16个），用插入排序或者选择排序完成子数组的排序。
 *
 *
 * 问题：递归会使得堆栈层次太深，大的数组会Stack Overflow
 * 优化：通过正向for循环计算拆分子数组
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Merge {

    private static Comparable[] aux;

    public static void main(String[] args) {
        String[] array = new String[] {"b", "c", "d", "a", "e", "x", "a", "m", "p", "l", "e"};
        sort(array);
    }

    public static void sort(Comparable[] array) {

        aux = new Comparable[array.length];

        sort(array, 0, array.length - 1);
    }

    public static void sort(Comparable[] array, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        if ((lo + 1) == hi) {
            compareAndExchange(array, lo, hi);
            show(array);
            return;
        } else {

            int mid = (lo + hi) / 2;
            sort(array, lo, mid);
            sort(array, mid + 1, hi);

            merge(array, lo, mid, hi);
            show(array);
        }
    }

    public static void merge(Comparable[] array, int lo, int mid, int hi) {
        int i = lo; //i: left side index
        int j = mid + 1; //j: right side index
        int k = lo;


        for (int l = lo; l <= hi; l++) {
            aux[l] = array[l];
        }

        while (true) {

            if (i > mid) {
                if (j > hi) {
                    break;
                } else {
                    array[k++] = aux[j++];
                }
            } else {
                if (j > hi) {
                    array[k++] = aux[i++];
                } else {
                    if (aux[i].compareTo(aux[j]) > 0) {
                        array[k++] = aux[j++];
                    } else {
                        array[k++] = aux[i++];
                    }
                }
            }
        }


    }

}
