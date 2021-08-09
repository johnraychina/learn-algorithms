package 每日一题;

import java.util.Arrays;

/**
 * <pre>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 输入：[1,3,2]
 * 输出：[3,1,2]
 *
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * </pre>
 */
public class 下一个排列31 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    //我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小
    //从末尾开始寻找最靠右的「较小数」：
    // 找第一个非降序的位置i，使得a[i]<a[i+1],  2 < 3，在即for x in [i+1, n)内都是降序的: a[x] > a[x+1]
    //然后在从区间[i+1, n) 内寻找最小的「较大数」数a[j]，使得a[j] > a[i]
    //然后交换，a[i]与a[j]的值，注意：[i+1, n)在交换前后都是降序的
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        // 从末尾开始寻找最靠右的「较小数」
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // [i+1, n-1) 都是降序的，从尾部找到第一个大于 a[i]的值 a[j]
            // 进行交换，得到新的排列 比原排列大，
            int j = n - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, j, i);
        } // 如果i==-1那么只需要reverse就行了

        // 然后我们把降序的[i+1, n-1)的通过双指针反转为升序，得到原排列的下一个更大排列。
        reverse(nums, i + 1, n - 1);
    }

    private static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    public static void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }
}
