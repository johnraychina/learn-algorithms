package com.zhangyi.algorithm.sort;

/**
 * <pre>
 * 快速排序: 2N*lnN
 *
 * 优化点：当数组已经有序，如果不用随机打乱，会导致pivot无法分隔为均衡的两半，比较次数变为N+(N-1)+...+1 = N(N-1)/2
 * 优化点：小数组问题（递归太多stack有开销），用插入排序法优化
 * 优化点：相较于随便用第一个元素作为pivot，而是选取一个靠近中间值的pivot: medianOf3
 *
 *
 *
 *
 * It's really an admission that although we often talk about average case complexity, we don't in practice expect
 * every case to turn up with the same probability.
 *
 * Sorting an already sorted array is worst case in quicksort, because whenever you pick a pivot, you discover that
 * all the elements get placed on the same side of the pivot, so you don't split into two roughly equal halves at all
 * . And often in practice this already sorted case will turn up more often than other cases.
 *
 * Randomly shuffling the data first is a quick way of ensuring that you really do end up with all cases turning up
 * with equal probability, and therefore that this worst case will be as rare as any other case.
 *
 * It's worth noting that there are other strategies that deal well with already sorted data, such as choosing the
 * middle element as the pivot.
 * </pre>
 *
 * @author Zhang Yi
 */
public class QuickSortSimple {

    public static void main(String[] args) {
        char[] a = "sortexample".toCharArray();
        sort(a);
        System.out.println(new String(a));
    }

    public static void sort(char[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(char[] a, int lo, int hi) {
        if (lo >= hi) { return; }
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    private static int partition(char[] a, int lo, int hi) {

        //a[lo] as the partition key
        int i = lo;
        int j = hi + 1;
        while (true) {
            //find item on the left to swap
            while (less(a[++i], a[lo])) {
                if (i == hi) { break; }
            }
            //find item on the right to swap
            while (less(a[lo], a[--j])) {
                //if (j == lo) break; //这里不是必须的，因为如果--j之后，j==ho时, less不成立跳出循环，轮不到这个if判断。
            }

            //check if the pointers cross
            if (i >= j) { break; }
            //exchange a[i] with a[j]
            exch(a, i, j);
        }

        //exchange a[lo] with a[j]
        exch(a, lo, j);

        return j;
    }

    private static void exch(char[] a, int i, int j) {
        char v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    private static boolean less(char a, char b) {
        return a < b;
    }

}
