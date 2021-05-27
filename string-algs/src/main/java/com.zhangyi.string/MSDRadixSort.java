package com.zhangyi.string;

/**
 * MSD与LSD不同的是：
 * 1. 可以支持长度不同的元素（后面补-1，让较短的排前面）；
 * 2. 分为R组后，要按每组切分递归；
 * <p>
 * Most-Significant-Digit string sort
 * - Partition array into R pieces according to first character
 * (use key-index counting)
 * - Recursively sort all string that start with each character
 * (key-indexed counts delineate sub arrays to sort)
 *
 * @author Zhang Yi
 */
public class MSDRadixSort {
    public static final int R = 256;

    public static void sort(String[] a) {
        int n = a.length;
        String[] aux = new String[n];
        sort(a, 0, n-1, 0, aux);
    }

    //sort from a[lo] to a[hi], starting at the d-th character
    public static void sort(String a[], int lo, int hi, int d, String[] aux) {

        if (hi <= lo) return;

        //todo why R+2, count[0:R+1]
        // 基数r正常的范围是[0, R]，为了处理负数, r要加1，变为[1,R+1]
        int[] count = new int[R+2];

        // compute frequency counts
        // count[x] 代表x 的有多少个元素的charAt[x]小于x，所以要+1，另外考虑负数，再+1
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        // transform into indices
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        // recursively sort a[lo...hi] for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    // compatible with variable length string
    private static int charAt(String s, int d) {
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    public static void main(String[] args) {
        String[] a = new String[]{"Alice ", "Bruce ", "Nilson", "Geogia", "Brad  ", "Adam  ", "Bob   "};
        sort(a);
        for (String e : a) {
            System.out.print(e + ", ");
        }
    }
}
