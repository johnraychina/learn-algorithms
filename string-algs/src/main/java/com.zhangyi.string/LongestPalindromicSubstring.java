package com.zhangyi.string;

/**
 * 最长回文子串
 * <pre>
 *
 * 动态规划解法:
 * 举例字符串 "aba" , "abba"
 * P(i,j)表示字符串s[i:j]是否为回文
 *
 * 1) 列出状态转移方程:
 * P(i,j) = P(i+1,j-1) && s[i] == s[j]
 *
 * 2) 初始条件
 * 当子字符串只有1个字符时，P(i,i) = true
 * 当子字符串只有2个字符时，P(i,i+1) == s[i] == s[i+1]
 *
 * 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，
 * 因此一定要注意动态规划的循环顺序。
 * </pre>
 * @author Zhang Yi
 */
public class LongestPalindromicSubstring {

    public static String getLongestPalindromicSubstring(String str) {

        int n = str.length();
        boolean[][] dp = new boolean[n][n];

        String ans = "";
        for (int subStrLen = 0; subStrLen < n; subStrLen++) {
            for (int i = 0; i + subStrLen < n; i++) {
                int j = i + subStrLen;

                if (subStrLen == 0) {
                    dp[i][j] = true;
                } else if (subStrLen == 1) {
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    dp[i][j] = dp[i+1][j-1] && str.charAt(i) == str.charAt(j);
                }

                if (dp[i][j] && subStrLen + 1 > ans.length()) {
                    ans = str.substring(i,j+1);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        String[] testSet = new String[]{"abc", "xabcbazzz", "xxabazz"};
        for (int i = 0; i < testSet.length; i++) {
            String subStr = getLongestPalindromicSubstring(testSet[i]);
            System.out.println(subStr);
        }
    }
}
