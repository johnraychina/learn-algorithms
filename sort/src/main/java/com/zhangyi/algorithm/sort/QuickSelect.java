package com.zhangyi.algorithm.sort;

import algs4.StdRandom;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.less;

/**
 * KEY IDEA: 找到第k个元素，平均而言，只需要线性时间！  线性时间！ 线性时间！
 * <pre>
 * 从直觉上看，每次只比较一半： N + N/2 + N/4 + .. + 1 ~ 2N 次比较
 *
 * 严格分析：C_N = 2N + 2k ln(N/k) + 2(N-k) ln(N/(N-k))，
 *
 * 当k=1时，C_N = 2N + 2klnN
 * 当K=N时，C_N = 2N
 * 当K=N/2时，C_N = 2N + 2N ln2
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class QuickSelect {

    /**
     * find the
     *
     * @param a
     * @param k
     * @return
     */
    public static <T extends Comparable<T>> T select(T[] a, int k) {

        StdRandom.shuffle(a);

        int lo = 0; //左侧指针
        int hi = a.length - 1; //右侧指针

        while (lo < hi) {

            // 将数组分为了左右两边，a[lo+1 ... j] <= a[lo] <= a[j+1 ... hi]
            // a[lo]作为分界元素，左侧指针 i从左lo+1到右扫描，右侧指针j从右到左扫描
            // 对应的最终位置为j
            // 最后交换 a[lo], a[j]，返回分界点j
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1; //我们要找的第k大的元素在j的右半部分
            } else if (j > k) {
                hi = j - 1; //我们要找的第k大的元素在j的左半部分
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {

        // a[lo]: take ti as the partition value
        // a[++i]: iterate over the left side of array
        // a[--j]: iterate over the right side of array
        T v = a[lo];
        int i = lo;
        int j = hi + 1;

        while (true) {

            // find one that larger than a[lo] in left side
            while (less(a[++i], v)) {
                if (i == hi) { break; }
            }

            // find one that smaller than a[lo] in
            while (less(a, lo, --j)) {
                if (j == lo) { break; }
            }

            // check if the pointers cross
            if (i >= j) { break; }

            exchange(a, i, j);
        }

        // put partition value to j
        exchange(a, lo, j);

        return j;
    }
}
