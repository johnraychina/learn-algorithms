package com.zhangyi.string;

/**
 * <pre>
 *
 * LSD 从最低位开始排序：a[i].charAt(d)，各元素长度必须相同才行。
 * 相关的题目：
 *
 * 49. 字母异位词分组
 * 179. 最大数
 * 164. 最大间距
 *
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
