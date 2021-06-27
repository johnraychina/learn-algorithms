package 每日一题;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括n 个整数的数组nums和 一个目标值target。
 * 找出nums中的三个整数，使得它们的和与target最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最接近的三数之和16 {


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(threeSumClosest_2pointers(nums, 1));
    }

    //组合问题
    //1. 暴力求解：三重循环O(n^3) + 一次排序O(n^3 log n^3)

    public static int threeSumClosest(int[] nums, int target) {

        int N = nums.length;
        int combinations = N * (N - 1) * (N - 2) / 6;
        int[] threeSums = new int[combinations];

        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    threeSums[x++] = nums[i] + nums[j] + nums[k];
                }
            }
        }

        Arrays.sort(threeSums);

        int diff = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;
        for (int i = 0; i < threeSums.length; i++) {
            int newDiff = Math.abs(threeSums[i] - target);
            if (newDiff < diff) {
                closestSum = threeSums[i];
                diff = newDiff;
            }
        }

        return closestSum;
    }


    //2. 剪枝：动态规划
    //目标：三数和
    //优化：接近target
    //状态：第一个数字，第二个数字，第三个数字


    // 3. 三数和转为两数和：遍历num[i]然后 target-num[i]作为两数和，
    // 然后双指针搜索 while(left<right)
    // nums = [-4,-1,1,2,1], target = 1
    public static int threeSumClosest_2pointers(int[] nums, int target) {

        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {

            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            //2 pointers to find closest 2-sum
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) { //move left++
                    int x = nums[left];
                    while (nums[left] == x && left < right) {
                        left++; // skip duplicates
                    }
                } else if (sum > target) {
                    int y = nums[right];
                    while (nums[right] == y && left < right) {
                        right--; // skip duplicates
                    }
                } else {
                    return target; // on equals, fast return
                }
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
            }
        }

        return closest;
    }
}
