package 每日一题;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * nums = [5,7,7,8,8,10], target = 8s
 */
public class 在排序数组中查找元素的第一个和最后一个位置34 {
    public static void main(String[] args) {
//        int[] ans = searchRange(new int[]{5,7,7,8,8,10}, 8);
        int[] ans = searchRange(new int[]{8, 8, 8, 8, 10}, 8);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }

    public static int[] searchRange(int[] nums, int target) {

        int first = -1;
        int last = -1;
        //为了解决边界问题，左边虚拟一个num[-1]=Integer.MIN_VALUE

        // first: 第一个大于等于target的元素位置
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int m = nums[mid];

            if (m < target) left = mid + 1;
            else {
                // 相等：为了找到最左边第一个等于target的元素，right朝左边移动
                right = mid - 1;
                first = mid; //需要另外一个指针，避免移动太多，mid-1和target不相等
            }

            System.out.printf("1. num[%s]=%s \n", mid, m);
        }


        // last
        left = 0;
        right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            int m = nums[mid];

            // 相等：为了找到最右边第一个比target大的元素，left朝右移动
            if (m <= target) left = mid + 1;
            else {
                right = mid - 1;
                last = mid;
            }
            System.out.printf("2. num[%s]=%s \n", mid, m);
        }

        // 左闭右开
        return new int[]{first, last - 1};
    }
}
