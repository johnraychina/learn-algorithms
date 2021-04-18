package 贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * 621. 任务调度器
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意: 可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1: 输入: [ [1,2], [2,3], [3,4], [1,3] ] 输出: 1
 * <p>
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2: 输入: [ [1,2], [1,2], [1,2] ] 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3: 输入: [ [1,2],[2,3] ]
 * 输出: 0 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 无重叠区间DP {

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] intervals3 = {{1, 2}, {2, 3}};
        int[][] intervals = {{1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
        //System.out.println(eraseOverlapIntervals(intervals1));
        //System.out.println(eraseOverlapIntervals(intervals2));
        //System.out.println(eraseOverlapIntervals(intervals3));
    }

    //解法一：动态规划，调度问题一样
    public static int eraseOverlapIntervals(int[][] intervals) {
        //区间i, left: interval[i][0], right: interval[i][1]
        //将问题转换为求 无重复 最多区间数
        //子问题：以i作为最后区间时，无重复最多区间数量 dp[i]
        //我们假设k={所有在i左边且不与i重叠的区间}，有状态转移方程：dp[i] = max{dp[k] + 1}
        //整体问题 = max{dp[i]}, i={0,1,..., n-1}
        //初始条件: dp[i] = 1，每个区间先初始化为1然后再迭代更新。
        int n = intervals.length;
        int dp[] = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        //想要找到i对应的k：{所有在i左边且不与i重叠的区间}
        //我们先按每个区间的右端点k_right进行排序，然后判断：k_right <= i_left 表示不重叠
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; //0:left, 1:right
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        //k={所有在i左边且不与i重叠的区间}
        //dp[i] = max{dp[k] + 1}
        //如果不重叠：k_right <= i_left 则更新
        int maxIntervals = 0;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < i; k++) {
                if (intervals[k][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[k] + 1);
                }
            }

            maxIntervals = Math.max(dp[i], maxIntervals);
        }

        return n - maxIntervals;
    }

    //解法二：贪心算法

}
