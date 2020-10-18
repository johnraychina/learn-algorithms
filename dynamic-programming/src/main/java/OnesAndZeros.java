/**
 * https://leetcode-cn.com/problems/ones-and-zeroes/ 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * <p>
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * <p>
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ones-and-zeroes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zhang Yi
 */
public class OnesAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {

        int dp[][] = new int[m + 1][n + 1];

        /**
         * 我们用 dp(i, j) 表示使用 i 个 0 和 j 个 1，最多能拼出的字符串数目，那么状态转移方程为：
         * dp(i, j) = max(1 + dp(i - cost_zero[k], j - cost_one[k]))
         *     if i >= cost_zero[k] and j >= cost_one[k]
         *
         * 其中 k 表示第 k 个字符串，cost_zero[k] 和 cost_one[k] 表示该字符串中 0 和 1 的个数。
         * 我们依次枚举这些字符串，并根据状态转移方程更新所有的 dp(i, j)。
         * 注意由于每个字符串只能使用一次（即有限背包），因此在更新 dp(i, j) 时，i 和 j 都需要从大到小进行枚举。
         *
         */
        for (int k = 0; k < strs.length; k++) {
            int[] count = count(strs[k]);
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    //状态转移：
                    //对于i,j有多个str[k]可选，要选使dp[i][j]最大的
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
                }
            }
        }
        return dp[m][n];

    }

    public int[] count(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - '0']++;
        }
        return c;
    }

}
