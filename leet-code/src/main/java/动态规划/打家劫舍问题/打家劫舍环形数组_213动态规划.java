package 动态规划.打家劫舍问题;

import java.util.Arrays;

/**
 * <pre>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 * 示例1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 打家劫舍环形数组_213动态规划 {

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n <= 3) { return Arrays.stream(nums).max().orElse(0); }

        //首尾相连的2个房子，有三种情况：case1:都不偷，case2:偷第0号房子，case3:偷第n-1号房子
        //偷一个肯定比都不偷要多赚，所以只需要考虑case2和case3
        return Math.max(simpleRob(nums, 0, n - 2), simpleRob(nums, 1, n - 1));
    }

    public static int simpleRob(int[] nums, int start, int end) {
        //观察到状态i只与前2个状态相关，只需要记录并更新前两个状态即可
        int pre = 0;
        int tmp = 0;
        int cur = 0;
        for (int i = start; i <= end; i++) {
            tmp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = tmp;
        }

        return cur;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1, 2, 3, 1}));
    }
}
