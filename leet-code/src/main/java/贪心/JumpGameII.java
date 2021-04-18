package 贪心;

/**
 * <pre>
 * 45. Jump Game II
 *
 * Given an arry of non-negative integers,
 * you are initially positioned at the first index of the array.
 *
 * Each element in the array presents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps
 * to the last index.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zhang Yi
 * </pre>
 */
public class JumpGameII {

    public int jump(int[] nums) {

        //假设：一个最优解S = 0-P1-P2-P3-Last
        //调整：如果L<=P3是能跳到Last最左位置，那么T= 0-P1-P2-L-Last也是一个最优解
        //注意：T以贪心选择开始
        //贪心策略：从终点开始，每次选择能跳到当前点的最左位置
        int jumpCnt = nums.length;
        int pos = nums.length - 1;
        while (pos > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (i + nums[i] >= pos) {
                    pos = i; //pos = L = i
                    jumpCnt++; //从L到pos跳一次
                    break;
                }
            }

        }

        return jumpCnt;
    }
}
