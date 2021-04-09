/**
 * @author Zhang Yi
 */
public class 二分搜索框架 {

    public static void main(String[] args) {
        System.out.println(binSearchA(new int[] {1, 2, 2, 3, 4}, 2));
        System.out.println(binSearchB(new int[] {1, 2, 2, 3, 4}, 2));
    }

    //寻找左侧边界的二分搜索（支持重复元素），来自《济公学院》
    public static int binSearchA(int[] nums, int target) {
        System.out.println("start search");

        int left = 0;
        int right = nums.length - 1;
        while (right - left > 1) { //todo [left...mid...right]  三者都不同
            showSearchArea(left, right);

            int mid = left + (right - left) / 2;

            if (nums[mid] < target) { left = mid; } else {
                right = mid; //nums[mid]大于或等于target，搜索区间向左收缩
            }
        }
        return right;
    }

    private static void showSearchArea(int left, int right) {
        System.out.printf("%d-%d\n", left, right);
    }

    //寻找左侧边界的二分搜索（支持重复元素），来自《济公学院》
    public static int binSearchB(int[] nums, int target) {
        System.out.println("start search");

        int left = 0;
        int right = nums.length - 1;
        while (left < right) { //todo [left...right], 左右只差1的情况下，mid向下取整会等于left
            showSearchArea(left, right);

            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1; //todo mid向下取整可能等于left，这里要+1，不然会导致死循环
            } else {
                right = mid; //nums[mid]大于或等于target，搜索区间向左收缩
            }
        }
        return right;
    }

    private static boolean processWith(int mid) {
        return false;
    }

    //常规二分搜索
    public int binSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //向下取整得到mid，如果有偶数个元素，那么mid会靠左
            int mid = left + (right - left) / 2;

            //nums从小到大，按升序排序
            //中间值太小则查右边，中间值太大则查左边
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        return -1;
    }

    //寻找左侧边界的二分搜索（支持重复元素）
    public int binSearchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length; //todo 注意：左闭右开[left, right)
        while (left < right) { //todo 注意
            //向下取整得到mid，如果有偶数个元素，那么mid会靠左
            int mid = left + (right - left) / 2;

            //nums从小到大，按升序排序
            //中间值太小则查右边，中间值太大则查左边
            //mid将搜索区间分割为2个区间：[left, mid) , [mid+1, right)
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid; //todo 这里注意，找到target后并不立即返回，而是向左缩小区间
            } else if (nums[mid] > target) {
                right = mid; //todo 注意，左闭右开
            }
        }

        return left; //todo 注意，没找到则返回最大的左边界
    }
}
