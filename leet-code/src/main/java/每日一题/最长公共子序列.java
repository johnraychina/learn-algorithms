package 每日一题;

/**
 * <pre>
 * 1143. 最长公共子序列 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。
 * 如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的子序列是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 * @author Zhang Yi
 */
public class 最长公共子序列 {

    public int longestCommonSubsequence(String text1, String text2) {
        //看到需要遍历的到最值的情况，那就是动态规划
        //我们用text1[0:i] 表示长度为i的前缀
        //状态dp[i][j]：text1[0:i] 与 text2[0:j] 的最长公共子序列长度

        int m = text1.length();
        int n = text2.length();
        int dp[][] = new int[m + 1][n + 1];

        //base case:
        //i=0时，dp[0][j]=0
        //j=0时，dp[i][0]=0

        //遍历 + 状态转移
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
