package 每日一题;

public class 搜索数组中排序数组33 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }
    //思路：利用相对有序做二分查找，判断是否有序：
    // 头<尾 则对比 target是否在区间内
    // 头 > 尾 不做对比（因为不是有序的）继续二分下去
    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        return search(nums, left, right, target);
    }

    private static int search(int[] nums, int left, int right, int target) {
        int mid = (left + right) / 2;

        if (nums[left] <= nums[right]) {
            if (nums[left] <= target && target <= nums[right]) {
                //继续二分查找，利用相对有序，只找一边
                if (target == nums[mid]) {
                    return mid;
                } else if (target > nums[mid]) {
                    return search(nums, mid + 1, right, target);
                } else {
                    return search(nums, left, mid, target);
                }
            }
            return -1; //区间有序，但是不在其中
        } else {
            //二分，先找做半区，没找到再找右半区
            int pos1 = search(nums, left, mid, target);
            if (pos1 != -1) return pos1;
            return search(nums, mid+1, right, target);
        }
    }
}
