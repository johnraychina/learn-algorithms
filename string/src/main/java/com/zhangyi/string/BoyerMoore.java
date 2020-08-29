package com.zhangyi.string;

/**
 * Boyer-Moore string search algorithm.
 *
 * <pre>
 * Intuition:
 * - Scan characters in pattern from right to left.
 * - Can skip as many as M text chars when finding one not in the pattern.
 *
 *  m = pattern.length
 *  n = text.length
 *
 * <em>从右向左</em>扫描pattern字符
 * 如果发现有不匹配字符的则skip跳过一些字符（最多M个）重新开始匹配。
 * 和KMP不同，这个算法会回退文本指针，但是最大程度利用回退。
 * 心理表征：将pattern字符看成固定的带锯齿的方块，将text看成带锯齿方块，从右向左滑动text做匹配。
 *
 * 从左到右扫描，挨个匹配text和pattern字符，当发现不匹配时，检查不匹配的text的字符是否在pattern中存在:
 * 如果不存在，直接将text滑动j+1个位置（体现在right表就是-1)
 * 如果存在，将pattern和text对齐，text的指针i就要移动skip个位置，然后从右边向左重新开始比较
 *      需要注意的是：
 *      1、如果有多个，以最右边的那个字符位置为准（记作right[c]）
 *      2、如果最右边的字符位置 在不匹配位置的右边，则说明无法利用这个信息，直接skip=1往右挪一位
 *
 *
 * How much to skip?
 * - Case 1. Mismatch character not in pattern.
 * increment i one character beyond the mismatch character.
 *
 * - Case 2a. Mismatch character in pattern.
 * align text 'N' with rightmost pattern 'N'
 *
 * - Case 2b.
 * Mismatch character in pattern (but heuristic no help): increment i by 1.
 *
 * How much to skip?
 * Pre-compute index of rightmost occurrence of character c in pattern(-1 if character not in pattern).
 *
 *
 * Boyer-Moore: analysis
 * Property: Substring search with the Boyer-Moore mismatched character heuristic
 * takes about ~N/M character compares to search for a pattern of length M in a text of length N.
 *
 * Worst case: Can be as bad as ~MN
 *
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class BoyerMoore {

    private final int R;
    private String pat;
    private int right[];

    public BoyerMoore(String pat) {
        R = 256;
        right = new int[R];
        int M = pat.length();

        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;

        for (int i = 0; i < N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    //txt.charAt(i + j) : from right to left
                    //if not match, calculate skip
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }

            if (skip == 0) return i; //match
        }
        return N;
    }

}
