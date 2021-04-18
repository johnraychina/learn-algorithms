package 贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping intervals
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the
 * intervals non-overlapping.
 * <p>
 * Example1:
 * <p>
 * Input: [[1,2], [2,3], [3,4],[1,3]] Output: 1 Explanation: [1, 3] can be removed and the rest of the intervals are
 * non-overlapping.
 *
 * @author Zhang Yi
 */
public class 无重叠区间Greedy {
    public int eraseOverlapIntervals(int[][] intervals) {

        //The intervals are sorted by "end" position as L1,L2...L4
        //Assume an optimal solution is L2,L3,L4
        //we can adjust L2 to L1 in order to get another optimal solution L1,L3,L4
        //since any interval ending earlier than L2 is compatible with L3,L4
        //Continuing the process provides a greedy algorithm.
        Arrays.sort(intervals, Comparator.comparingInt(e -> e[1]));

        //take the first intervals[0]
        int count = 1;
        int end = intervals[0][1];

        //iterate the rest intervals
        //to get maximum number of non-overlapping intervals
        for (int i = 1; i < intervals.length; i++) {
            //if not overlap then update end and count
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }

        return intervals.length - count;
    }
}
