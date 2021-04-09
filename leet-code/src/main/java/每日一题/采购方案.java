package 每日一题;

import java.util.Arrays;

/**
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 *
 * @author Zhang Yi
 */
public class 采购方案 {
    public static void main(String[] args) {
        //排序+双指针
        System.out.println(purchasePlans(new int[] {2, 5, 3, 5}, 6));
        System.out.println(purchasePlans(new int[] {2, 2, 1, 9}, 10));
    }

    public static int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);

        //start...i....j...k...end
        //i为左边界， 然后k为右边界，i和k都会向中间收缩，相遇则结束
        int i = 0;
        int k = nums.length - 1;
        int ans = 0;
        while (i >= 0 && i < k) {
            if (nums[i] + nums[k] > target) {
                k--;
            } else {
                ans += (k - i);
                i++;
            }
        }
        return ans;
    }
}
