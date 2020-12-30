package com.zhangyi.string;

/**
 * <pre>
 * 正则表达式乞丐版，支持.和*匹配，但不支持()和|
 *
 *
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class RegexSimple {

    public static void main(String[] args) {

        boolean match = new RegexSimple().isMatch("Yahoo!", "Ya.*");
        System.out.println(match);
    }

    public boolean isMatch(String s, String p) {

        //使用动态规划
        //dp[i][j] = true/false 表示s的前i个字符与p的前j个字符匹配情况
        //动态规划的边界条件为 f[0][0] = true，即两个空字符串是可以匹配的。
        // 最终的答案即为 f[m][n].其中 m 和 n 分别是字符串 s 和 p的长度。
        // 由于大部分语言中，字符串的字符下标是从 0 开始的，因此在实现上面的状态转移方程时，需要注意状态中每一维下标与实际字符下标的对应关系。

        //状态转移:
        //if p[j] == '*'
        //if match(s[i], p[j-1]): dp[i][j] = dp[i-1][j] or dp[i][j-2]
        //else: dp[i][j] = dp[i][j-2]

        //if p[j] != '*'
        //if matches(s[i], p[j]): dp[i][j] = dp[i-1][j-1]
        //otherwise: return false
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, i, p, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }

                } else {
                    if (matches(s, i, p, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    //else { return false; }
                }
            }

        }

        return dp[m][n];
    }

    //i表示字符串s的前缀长度，j表示模式字符串p的前缀长度, s[i]第i个字符, p[j]表示第j个字符，对应到字符串下标时，需要减一
    private boolean matches(String s, int i, String p, int j) {
        if (i == 0) { return false; }

        if (p.charAt(j - 1) == '.') {
            return true;
        }

        return (p.charAt(j - 1) == s.charAt(i - 1));
    }

}
