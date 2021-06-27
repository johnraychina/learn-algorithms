/**
 * 最长回文串
 *
 * @author Zhang Yi
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        //子问题：最长回文串的s(i,j)，s(i) == s(j)
        // 子串也是回文串s(i+1,j-1)，其中len(s(i,j)) >2
        // dp[i][j] 表示子串s(i,j)是否为回文
        // dp[i][j] = dp[i+1][j-1] and s(i)==s(j) for len>2

        // 初始条件和边界条件：
        // i < j时为非法条件，初始化dp[i][j]=false for i < j
        // 长度为1的子串一定是回文，初始化dp[i][j]=true for len==1, i in [0,n)
        // 长度为2的子串：如果s[i]==s[j]则是回文，否则不是回文
        // 最终问题转换为：求 ArgMax() { dp[i][j] == true, for i in [0,n), j in [i,n) }

        int n = s.length();

        //初始化
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        String ans = "";
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) { //这里注意边界条件，j=i+len < n
                int j = i + len;

                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }

                if (dp[i][j] && len + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("13333333333x12abababb0929123"));
    }
}
