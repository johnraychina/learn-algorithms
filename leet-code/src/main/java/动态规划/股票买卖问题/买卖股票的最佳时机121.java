package 动态规划.股票买卖问题;

/**
 * <pre>
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 买卖股票的最佳时机121 {

    //暴力破解法：
    //内循环：固定买入日期a_i，再遍历后续卖出日期b_j, 得到a_i的max_profit = max{b_j -a}
    //外循环：遍历a_i得到max_profit
    public static int maxProfit_BruteForce(int[] prices) {
        int maxProfit = 0; //默认没有交易，利润0
        int days = prices.length;
        for (int i = 0; i < days; i++) {
            for (int j = i; j < days; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }

    //一次遍历，记录当前最低价
    public static int maxProfit_OnePass(int[] prices) {
        int maxProfit = 0; //默认没有交易，利润0
        int days = prices.length;
        int minPrice = Integer.MAX_VALUE; //记录最小价格，初始为最大值，用于做区分

        for (int i = 0; i < days; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        //暴力遍历, 一次遍历
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit_BruteForce(prices));
        System.out.println(maxProfit_OnePass(prices));

        prices = new int[] {7, 6, 4, 3, 1};
        System.out.println(maxProfit_BruteForce(prices));
        System.out.println(maxProfit_OnePass(prices));

    }
}
