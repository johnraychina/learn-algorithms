package 剑指offer;

/**
 * <pre>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 青蛙跳台阶问题 {

    public static void main(String[] args) {
        System.out.println(numWays(0));
        System.out.println(numWays(1));
        System.out.println(numWays(2));
        System.out.println(numWays(10));
    }

    //fixme:

    /**
     * 空间复杂度优化：
     * <p>
     * 若新建长度为 nn 的 dpdp 列表，则空间复杂度为 O(N)O(N) 。
     * <p>
     * 由于 dpdp 列表第 ii 项只与第 i-1  和第 i-2  项有关，因此只需要初始化三个整形变量 sum, a, b ，利用辅助变量 sum 使 a, b  两数字交替前进即可 （具体实现见代码） 。 因为节省了 dp
     * 列表空间，因此空间复杂度降至 O(1)O(1) 。
     * <p>
     * 作者：jyd 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/solution/mian-shi-ti-10-ii-qing-wa
     * -tiao-tai-jie-wen-ti-dong/ 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int numWaysV2(int n) {
        int a = 1, b = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static int numWays(int n) {
        //动态规划问题：
        //整体问题：从开始位置跳到最后位置，总共有多少种跳法
        //子问题：从当前位置，有多少种跳法跳到最后位置，我们用变量i表示当前位置（1到n）
        //我们用dp[i] 表示从开始1跳到当前位置i的跳法数量
        //青蛙可以跳1级或者2级，所以有状态转移方程: dp[i] = dp[i-1] + dp[i-2]
        //初始条件：dp[0]=1, dp[1]=1
        //结果：dp[n]
        if (n < 2) {
            return 1;
        }
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            //避免越界
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }
}
