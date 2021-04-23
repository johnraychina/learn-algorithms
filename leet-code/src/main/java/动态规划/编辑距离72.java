package 动态规划;

/**
 * @author Zhang Yi
 */
public class 编辑距离72 {
}
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 1559 👎 0

class Solution {
    public static int minDistance(String word1, String word2) {
        // 目标 minimum distance
        // 状态: word的前缀长度: i, j, dp[i][j] = min distance between word1[0:i) and word2[0:j)
        // 状态转移： 添加/删除/替换
        int I = word1.length();
        int J = word2.length();
        int dp[][] = new int[I + 1][J + 1];

        for (int i = 0; i < I + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < J + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < I + 1; i++) {
            for (int j = 1; j < J + 1; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //添加/删除/替换
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[I][J];
    }

    public static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public static void main(String[] args) {
        //System.out.println(min(1, 2, 3));
        System.out.println(minDistance("nice", "mice") == 1);
        System.out.println(minDistance("nice", "nike") == 1);
        System.out.println(minDistance("nice", "ice") == 1);
        System.out.println(minDistance("nice", "icecream") == 6);

    }
}
