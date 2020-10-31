/**
 * <pre>
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。
 * 如果没有这样的路线，则输出 -1。
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class CheapestPrice {
    public static void main(String[] args) {

        //变量：航班={u城市，v城市，价格}，中转数（距离）
        //目标 = min{ distPrice(src, v, 中转数k)}
        //子问题：min{ distPrice(src, u, 中转数k-1) + distPrice(u,v,中转数1)}
        // for (u, v) in flights
        // and k in [0, #middle cities]
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, K = 1;

        //distPrice[k][v] 从src城市到v城市，最多经过k个中转的，最便宜的价格
        int[][] distPrice = new int[K + 1][n];

        //初始化为最大值，以便被min拒绝
        for (int i = 0; i < K + 1; i++) {
            for (int j = 0; j < n; j++) {
                distPrice[i][j] = Integer.MAX_VALUE;
            }
        }

        //k=0, 从 src 到城市 v=flight[1] 价格为flight[2] 初始化src直达v的价格
        // distPrice[k][v] = flight[2]
        for (int[] flight : flights) {
            if (flight[0] == src) {
                distPrice[0][flight[1]] = flight[2];
            }
        }

        //to src的任何k都可以被初始化（dstPrice[][src]列初始化）
        for (int k = 0; k < K + 1; k++) {
            distPrice[k][src] = 0;
        }

        //k=0直达的已经初始化过了，从
        for (int k = 1; k <= K; k++) {
            for (int[] flight : flights) {
                //最小价格 = min{ distPrice(src, u, 中转数k-1) + distPrice(u,v,中转数1)}
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];
                if (distPrice[k - 1][u] != Integer.MAX_VALUE) {
                    distPrice[k][v] = Math.min(distPrice[k - 1][u] + price, distPrice[k][v]);
                }
            }
        }

        System.out.printf("%d to %d, the Lowest price of distance %d is %d", src, dst, K, distPrice[K][dst]);
    }

}
