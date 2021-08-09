package com.zhangyi.string;

import algs4.In;

/**
 * <pre>
 * DFA is abstract string searching machine.
 * - Finite number of states (including start and halt)
 * - Exactly one transition for each char in alphabet
 * - Accept if sequence of transitions lead to halt state.
 *
 * 暴力算法的问题在于每次都回退text的指针，实际上是一个浪费（因为我们已经知道部分信息了）。
 * 所以Knuth Morris Pratt 想：能不能用一个巧妙的办法来规避回退text指针呢？
 *
 *
 * 生成一个匹配状态前进 和回退的状态转换表：
 * pattern模式字符 生成一个DFA状态转换表（本质是一个状态转换图）
 * 对应DFA状态转换表，(s_from, character, s_to)
 * 形式1：行 = 匹配字符，列 = 当前状态，表格值= 下个状态
 * 形式2： 行 = 下个状态，列 = 当前状态，表格值 = 匹配字符
 *
 * 选取形式1，从字符(状态转移边)的序列模型 构造成状态(顶点)模型.
 * j = 列 = 当前状态
 * i = 边 = 匹配字符
 * pat.charAt(j) = 匹配字符 dfa[i][j] = 下个状态
 *
 * 将text和pattern匹配
 * DFA用于保存pattern字符序列的前后相关性。
 * 在text和pattern不匹配时，不对text做回退，
 * 利用已知信息（前面有j-1个匹配值）以及DFA中pattern自身序列相关性，算出下次从pattern哪个位置开始，然后对比。
 *
 * 小结：这个KMP算法适用于模式字符串中重复度比较高的搜索，本质是用状态回退代替text回退。
 * 它使用dfa保存了状态转换，匹配失败的回退状态可以通过数组的随机访问快速获得。
 *
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class KMP {

    public static final int NOT_FOUND = -1;
    private final String pat;
    private int M; //the length of pattern characters
    private int[][] dfa; //DFA table
    private int R; //radix of ASCII

    public KMP(String pat) {

        //Construction:
        //match transition: if in state j and next char c == pat.charAt(j), go to j+1
        //mismatch transition: if state j and next char c != pat.charAt(j),
        //then the last j-1 characters of input are pat[1...j-1], followed by c.
        // 为何是从1而不是0开始: 因为匹配失败要回退text的指针i，然后往右移动一位，i=i-j, 重新扫描已知的文本字符, DFA的状态是什么？
        // 我们其实不想回退文本指针，只是想将DFA重置到适当的状态，就好像已经回退过文本指针一样！！！
        // 这里的关键在于：需要重新扫描的文本正是pat[1 ~ j -1]，忽略首字母是因为模式需要右移一位，忽略最后一个字符是因为匹配失败。
        // 这些模式中的字符都是已知的，因此对于每个可能失败的位置都能预先找到重启DFA的正确状态。
        //To compute dfa[c][j] on DFA and take transition c.
        //Running time: takes only constant time if we maintain state X.
        //set dfa[c][j] = dfa[c][X]; //for mismatch
        //set dfa[pat.charAt(j)][j] = j +1; //match
        //update X = dfa[pat.charAt(j)][X] //update X

        R = 256;
        this.pat = pat;
        M = pat.length();
        dfa = new int[R][M + 1];

        //init:  status transition first character match: 0 -> 1
        dfa[pat.charAt(0)][0] = 1;

        //遍历计算状态j（列）
        for (int X = 0, j = 1; j < M; j++) {
            //遍历字母表c(行）, R=radix of character.
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }

    }

    public int search(In in) {
        int i, j;
        for (i = 0, j = 0; !in.isEmpty() && j < M; i++) {
            j = dfa[in.readChar()][j]; //next state j = dfa[match in char] [current state]
        }
        if (j == M) { return i - M; } else { return NOT_FOUND; }
    }

    public int search(String txt) {
        int i, j, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j]; //next state j = dfa[match txt char] [current state]
        }
        if (j == M) { return i - M; } else { return N; }
    }
}
