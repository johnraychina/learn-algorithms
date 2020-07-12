package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.isSorted;
import static com.zhangyi.algorithm.sort.Utils.less;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * 归并排序
 * <p>
 * 采用分而治之的思想，先拆分为2个子数组，分别排序，然后再将2个有序子数组合并为一个有序数组； 用递归的办法一直拆分到最后子数组只有2个或1个元素，简单比较合交换即可。
 * </p>
 * <p>
 * 问题：递归太多层影响执行速度 优化：拆分子数组到一定粒度就不再拆分（比如16个），用插入排序或者选择排序完成子数组的排序。
 * <p>
 * <p>
 * 问题：递归会使得堆栈层次太深，大的数组会Stack Overflow 优化：通过正向for循环计算拆分子数组
 *
 * @author 张义 johnraychina@163.com
 */
public class MergeSort<T extends Comparable<T>> {

    private static Comparable[] aux;

    public static void main(String[] args) {
        String[] array = "m e r g e s o r t e x a m p l e".split(" ");
        sort(array);
    }

    public static <T extends Comparable<T>> void sort(T[] array) {

        aux = new Comparable[array.length];

        sort(array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // optimization: cost of auxiliary array is too high for small array

        if (hi - lo < 7) {
            // todo apply insertion sort and return
        }
        int mid = (lo + hi) / 2;

        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        // optimization: early stop
        if (less(array, mid, mid + 1)) {
            return;
        }

        //todo optimization: switch role of array and auxiliary array could save time(but not space).
        merge(array, lo, mid, hi);
        show(array);

    }

    public static <T extends Comparable<T>> void merge(T[] array, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(array, lo, mid);
        assert isSorted(array, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int i = lo; //i: left side aux[lo ... mid]
        int j = mid + 1; //j: right side aux[mid+1 ... hi]

        // compare left side,  auxiliary array
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array[k] = (T)aux[j++]; // left side is finished, just copy right side
            } else if (j > hi) {
                array[k] = (T)aux[i++]; // right side is finished, just copy left side
            } else if (less(aux, j, i)) {
                array[k] = (T)aux[j++]; // copy the smaller element
            } else {
                array[k] = (T)aux[i++]; // copy the smaller element
            }
        }

    }

}
