package 打家劫舍问题;

/**
 * <pre>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *    偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 打家劫舍_198动态规划 {

    public static int rob(int[] nums) {
        //不想触发报警：隔着房子偷窃
        //金额最大化

        //动态规划解法
        //目标：偷窃总金额
        //状态：房屋号码i, 偷窃/不偷

        //目标状态定义：dp[i] 表示小偷走到到第i号房时的总偷窃金额
        //如果不偷i号房，为了最大化金额，他会偷[0~i-1]内的房屋
        //如果偷i号房，为了最大化金额，他会偷[0~i-2]内的房屋
        //状态转移：dp[i] = max{ dp[i-1] , dp[i-2] + nums[i]};
        //初始条件：dp[0] = num[0] 只有一个房子
        //dp[1] = max(num[0], num[1]) 2个房子中挑一个最大金额的c
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    }
}













