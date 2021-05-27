package com.zhangyi.string;

/**
 * <pre>
 *
 * LSD(Least-Significant-Digit) 从重要性最低位开始排序：
 * a[i].charAt(d)
 * 注意：数组a[]中各元素长度必须相同才行。
 *
 * 原理：利用一个count[R]数组 + aux[N]辅助数组，多次排序。因为是stable的算法（相同key排序前后不会交叉），所以可以从低位开始排。
 * 时间复杂度：O(RN)
 * 空间复杂度：O(N)
 *
 * 相关的题目：
 *
 * 49. 字母异位词分组
 * 179. 最大数
 * 164. 最大间距
 *
 * Frequency counts
 * Sorting uniformly distributed data
 * American flag sort
 * 2-sum
 * Binary search in a sorted array of strings
 * </pre>
 *
 * @author Zhang Yi
 */
public class LSDRadixSort {

    /**
     * Rearranges the array of w-character strings in ascending order.
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(String[] a, int w) {

        int n = a.length;
        int R = 256; //基数个数,ASCII码
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {

            //count key
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            //accumulate count
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            //sort one digit build aux
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            //copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }

            //next digit
        }
    }

    public static void main(String[] args) {
        String[] a = new String[]{"Alice ", "Bruce ", "Nilson", "Geogia", "Brad  ", "Adam  ", "Bob   "};
        sort(a, 6);
        for (String e : a) {
            System.out.print(e + ", ");
        }
    }
}
