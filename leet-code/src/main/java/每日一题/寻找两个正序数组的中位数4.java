package 每日一题;

import java.util.Arrays;

/**
 * <pre>
 * 4. 寻找两个正序数组的中位数
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * @hint mergesort
 * @hint 二分查找
 * </pre>
 */
public class 寻找两个正序数组的中位数4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int[] nums = new int[n];

        int i = 0;
        int j = 0;
        int x = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i >= nums1.length) {
                nums[x++] = nums2[j++];
            } else if (j >= nums2.length) {
                nums[x++] = nums1[i++];
            } else if (nums1[i] <= nums2[j]) {
                nums[x++] = nums1[i++];
            } else {
                nums[x++] = nums2[j++];
            }
        }

        Arrays.sort(nums);
        if (n % 2 == 1) {
            return nums[n / 2];
        } else {
            return (nums[(n - 1) / 2] + nums[(n + 1) / 2]) / 2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
