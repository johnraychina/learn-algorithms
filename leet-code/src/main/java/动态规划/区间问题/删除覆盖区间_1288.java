package 动态规划.区间问题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * 1288. 删除被覆盖区间
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 *
 * 示例：
 * 输入：intervals = [[1,4],[3,6],[2,8]]
 * 输出：2
 *
 * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
 * 提示：
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * 对于所有的 i != j：intervals[i] != intervals[j]
 * </pre>
 *
 * @author Zhang Yi
 */
public class 删除覆盖区间_1288 {

    public static void main(String[] args) {
        removeCoveredIntervals(new int[][] {{1, 4}, {3, 6}, {2, 8}});
    }

    //分析
    //先按区间start+end位置排序
    //然后判断重叠情况：case1 完全不搭界，case2 有重叠但是没包含 case3 包含
    //（通过打标）删除被覆盖区间（小的那个区间）
    //简化：由于做了排序，我们判断重叠时，只需要简单判断是否被上一个区间覆盖
    public static int removeCoveredIntervals(int[][] intervals) {

        if (intervals.length <= 1) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                //按区间的start位置排序，start小的排前面
                //按区间的end位置排序，end小的排后面
                //这样能保证第一个元素可以作为prev区间
                if (a[0] != b[0]) { return a[0] - b[0];} else { return b[1] - a[1]; }
            }
        });

        int prev = 0;
        int removed = 0;
        for (int i = 1; i < intervals.length; i++) {
            //被上个区间包含则删除本区间
            if (containedByPrev(intervals, i, prev)) {
                removed++;
                continue;
            } else {
                //更新prev区间，作为下个区间的判断依据
                prev = i;
            }
        }

        return intervals.length - removed;
    }

    private static boolean containedByPrev(int[][] intervals, int i, int prev) {
        return intervals[prev][0] <= intervals[i][0]
            && intervals[prev][1] >= intervals[i][1];
    }

    int removeCoveredIntervalsLabuladong(int[][] intvs) {
        // 按照起点升序排列，起点相同时降序排列
        Arrays.sort(intvs, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 记录合并区间的起点和终点
        int left = intvs[0][0];
        int right = intvs[0][1];

        int res = 0;
        for (int i = 1; i < intvs.length; i++) {
            int[] intv = intvs[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intvs.length - res;
    }
}




















