package 动态规划.区间问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 区间合并_56 {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = merge(intervals);
        for (int[] interval : res) {
            System.out.println(interval[0] + "-" + interval[1]);
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        //先按start排序，最小的start排最前
        //双指针，判断重叠：
        //case1：包含，则忽略
        //case2：重叠（不包含），则合并区间
        //case3：无重叠，则添加区间
        //prev.end >= cur.start 可以得到case1 和case2
        //然后再判断prev.end < cur.end 可以得到case2

        //合并：prev + cur  -> [prev.start, cur.end]
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        ArrayList<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]); //将第一个区间作为merged区间，避免越界
        for (int i = 1; i < intervals.length; i++) {

            int[] prev = merged.get(merged.size() - 1);
            int[] cur = intervals[i];
            if (prev[1] >= cur[0]) {
                if (prev[1] < cur[1]) {
                    prev[1] = cur[1];
                }
            } else {
                merged.add(cur);
            }
        }

        return merged.toArray(new int[][] {});
    }
}















