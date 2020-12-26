package com.zhangyi.algorithm.sort;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * @author 张义 johnraychina@163.com
 */
public class QuickSort {
    public static void main(String[] args) {
        String[] array = "q u i c k s o r t e x a m p l e".split(" ");
        sort(array);
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        show("--------------Normal QuickSort-----------");
        sort(a, 0, a.length - 1);

        show("--------------3-Way QuickSort-----------");
        //threeWayQuickSort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {

        if (lo >= hi) { return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);

        show(a);
    }

    /**
     * 3-way partitioning for duplicate keys.
     *
     * N lg N when all distinct;
     * linear when only a constant number of distinct keys
     *
     * <pre>
     * Goal:
     * ・Entries between lt and gt equal to partition item v.
     * ・No larger entries to left of lt.
     * ・No smaller entries to right of gt.
     *
     *
     *      [v] [..............................................................]
     *      |    |                                                            |
     *      lt   i                                                           gt
     *
     *
     *      [ little than v ] [v ... v] [......................] [greater than v]
     *                         |         |                    |
     *                        lt         i                   gt
     *
     *
     *      [ little than v ] [v ......... v] [...........greater than v.......]
     *                         |           |
     *                        lt           gt
     *
     *
     *
     * </pre>
     *
     * @param a
     * @param lo
     * @param hi
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> void threeWayQuickSort(T[] a, int lo, int hi) {
        show(a);
        if (lo <= hi) {
            return;
        }

        // Let v be partitioning item a[lo]
        // Scan i from left to right
        // - a[i] < v: exchange a[i] with a[lt], and increment both i and lt
        // - a[i] > v: exchange a[i] with a[gt], and decrement gt
        // - a[i] == v: increment both i
        T v = a[lo];
        int i = lo;
        int lt = lo;
        int gt = hi;

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exchange(a, i++, lt++);
            } else if (cmp > 0) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }

        threeWayQuickSort(a, lo, lt - 1);
        threeWayQuickSort(a, i, hi);
        show(a);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {

        //a[lo]作为分区的中间元素
        //小于它的准备放左边，大于它的准备放右边
        T mid = a[lo];

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
