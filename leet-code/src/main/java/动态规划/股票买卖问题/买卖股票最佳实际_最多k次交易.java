package 动态规划.股票买卖问题;

/**
 * <pre>
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 买卖股票最佳实际_最多k次交易 {

    //考虑一下，贪心策略行得通吗：一次遍历得到连续上升区间差值，贪心选择最大的几个区间？
    //举个反例[]， 原因：不像无限次交易，区间可以任意切分
    //限制次数的话，不用连续上升，可能退一步海阔天空，短暂性地下跌，后续涨起来也可能得到最大的收益。

    /**
     * @see
     * <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/yi-chong-ji-yu-wqs-er-fen-de-you-xiu-zuo-x36r/">
     *     基于wqs二分法（国外也叫Alien Trick)</a>
     * @param k
     * @param prices
     * @return
     */

    /**
     * 动态规划
     *
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {

        if (k == 0 || prices.length == 0) {
            return 0;
        }

        //最优化目标：最大利润dp
        //状态列举：
        //日期i：[0~n)
        //买卖次数n: [0~k]，一买一卖算1次
        //买卖状态s: {0, 1}（空仓休息0->0,满仓卖出1->0,  空仓买入0->1, 满仓休息1->1）
        int n = prices.length;
        int s = 2;
        int dp[][][] = new int[n][k + 1][s];

        //初始条件
        //k=0表示不能买卖, 状态s只能为0，s=1表示满仓，这种情况是不可能的，用负无穷大表示不可能
        //对日期遍历
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        //里面累加注意越界，为了简化，本题目输入做了一下限制：
        //0 <= k <= 100
        //0 <= prices.length <= 1000
        //0 <= prices[i] <= 1000
        //状态转换
        for (int i = 0; i < n; i++) {
            for (int count = k; count > 0; count--) {
                if (i == 0) {//第一天特殊处理
                    dp[0][count][0] = 0; //第一天，状态s=0空仓，无交易，此时利润为0
                    dp[0][count][1] = -prices[i]; //第一天，s=1买入
                    continue;
                }

                //当前第i天空仓状态，上一天i-1来源：空仓休息0->0,满仓卖出1->0
                dp[i][count][0] = Math.max(dp[i - 1][count][0], dp[i - 1][count][1] + prices[i]);
                //当前第i天满仓状态，上一天i-1来源：满仓休息1->1，空仓买入0->1
                dp[i][count][1] = Math.max(dp[i - 1][count][1], dp[i - 1][count - 1][0] - prices[i]);
            }
        }

        //最后利润：最后一天n-1, 允许最大买卖次数下count(0, k)，空仓状态s=0
        return dp[n - 1][k][0];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[] {3, 2, 6, 5, 0, 3}));
    }
}
