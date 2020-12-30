package huawei;

/**
 * @author Zhang Yi
 */
public class Test {
    public static void main(String[] args) {

        int m = 3;
        int price[] = {2, 4, 1}; //期货价格
        int n = 2; //最多交易次数

        //不能同时参与多笔交易（必须再次购买前出售之前的期货）
        //1.低买高卖
        //2.子问题：1次买卖机会，2次买卖机会
        //先看一次买卖，后面再看多次买卖

        int maxProfit = 0;

        //记录区间
        boolean[] occupied = new boolean[m + 1];

        //dp[i][j]表示第i天买，第j天卖，所得最大收益(可能为负数），取最大值即可
        //另外由于不能卖空，只能先买后卖，约束条件 i<=j，对于i==j，则dp=0
        //dp数组索引位置从1开始
        int[][] dp = new int[m + 1][m + 1];

        //left-right 记录本次实际买卖点
        int left = 0, right = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (i >= j) {
                    dp[i][j] = 0;
                } else { //i <j
                    dp[i][j] = price[j] - price[i];
                    left = i;
                    right = j;
                    //i不变同一行比较
                    if (dp[i][j] < dp[i][j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                        right = j - 1;
                    }
                    //j不变同一列比较
                    if (dp[i][j] < dp[i - 1][j]) {
                        dp[i][j] = dp[i - 1][j];
                        left = i - 1;
                    }
                }
            }
        }

        for (int i = left; i <= right; i++) {
            occupied[i] = true;
        }

        maxProfit += dp[m][m];

        //由于不能同时参与多笔交易（必须再次购买前出售之前的期货）
        //第一次买卖完成后，这段区间就被占用了（要标记这个区间，后续操作不能再这个区间执行）
        //第二次买卖只能在其他区间中执行
        for (int i = 0; i < m; i++) {
            //todo

        }
    }
}
