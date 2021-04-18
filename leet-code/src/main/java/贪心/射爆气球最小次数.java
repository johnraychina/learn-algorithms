package 贪心;

/**
 * <pre>
 * There are some spherical balloons spread in two-dimensional space. For each balloon,
 * provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter,
 * and hence the x-coordinates of start and end of the diameter suffice.
 * The start is always smaller than the end.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
 *
 * Given an array points where points[i] = [xstart, xend],
 * return the minimum number of arrows that must be shot to burst all balloons.
 *
 *
 * Example 1:
 *
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 射爆气球最小次数 {
    public int findMinArrowShots(int[][] points) {
        return 0;
    }
}
