package 每日一题;

/**
 * 11. 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 *
 * @hint 双指针
 */
public class 盛水最多的容器11 {
    public int maxArea(int[] height) {


        //从暴力求解启发出动态规划
        //暴力求解：对每个height[i]，计算i~n-1 的水，通过max得到较大值，两层循环，时间复杂度O(n^2)

        //水量 = 宽度 * 高度
        // 高度=左右两边的较小值，最宽的[0:n-1]不能保证最大水量，因为高度不是最高
        // 所以需要牺牲宽度，换取高度，从而使得整体最优
        // 双指针遍历左右两边，缩小宽度，必须确保整体高度变高，时间复杂度O(n)
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            //固定右边，遍历左边，找到较大的值，再移动左边界
            int l = left + 1;
            //todo
            while (height[left] < height[l]) {
                left++;
                maxArea = Math.max(maxArea, Math.min(left, right) * (right - left));
            }
            if (height[right] < height[right - 1]) {
                right--;
                maxArea = Math.max(maxArea, Math.min(left, right) * (right - left));
            }

        }

        return maxArea;
    }
}
