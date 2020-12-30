package 剑指offer;

/**
 * https://leetcode-cn.com/problems/integer-break/ 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * @author Zhang Yi
 */
public class 整数拆分 {

    public static void main(String[] args) {

    }

    public int integerBreak(int n) {

        /**
         * x = x_a + x_b
         * 观察:
         * 1） x_a>1 and x_b > 1时才有x_a * x_b > x
         * 2） x_a==1 or x_b == 1时，x_a * x_b = x - 1
         *
         * for x ==1，不可拆分；
         * 对于x=2, 1*1 < 2
         * 对于x=3, 1*2 < 3
         * 对于x=4, 1*3 < 4 == 2*2
         * 对于x=5, 1*4 < 5 < 3*2
         * 对于x=6, 6 < 4*2 == 2*2*2 < 3*3
         * 对于x=7, 7 < 5*2 < 3*4 == 3*2*2
         * 对于x=8, 8 < 2*6 ==3*4*1 < 2*2*2*2 == 4*2*2== 4*4 < 2*3*3
         *
         * 通过观察可以发现，当整数x拆分为{2,3}之后，进行拆分不会使乘积增大；
         * x>4时，进行拆分，分解为3，x-3时，总能使得总乘积增大或者不变；
         *
         *
         * 可以采用动态规划进行求解
         * 假设正整数i, 当i>=4时，可以拆分为(i-x)+ x， 此时x in {2, 3}, i-x>=2
         * 对比拆分后的值，取最大值即可：
         * dp[i] = Max{ x*(i-x), x*dp[i-x], dp[i] } x={2,3}
         * 展开为：
         * dp[i] = Max{ 2*(i-2), 2*dp[i-2], 3*(i-3), 3*dp[i-3], dp[i] }
         *
         * 目标：拆分数最大乘积
         * 优化函数：Max
         * 结果：dp[n]
         * 边界条件: dp[0]=0, dp[1]=1, dp[2]=2, dp[3]=3
         */

        if (n < 4) {
            return n - 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = max(2 * (i - 2), 2 * dp[i - 2], 3 * (i - 3), 3 * dp[i - 3]);
        }

        return dp[n];
    }

    private int max(int i1, int i2, int i3, int i4) {
        return Math.max(i1, Math.max(i2, Math.max(i3, i4)));
    }
}
