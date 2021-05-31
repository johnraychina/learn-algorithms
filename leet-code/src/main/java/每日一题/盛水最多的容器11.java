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

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    public static int maxArea(int[] height) {


        //从暴力求解启发出动态规划
        //暴力求解：对每个height[i]，计算i~n-1 的水，通过max得到较大值，两层循环，时间复杂度O(n^2)

        //水量 = 宽度 * 高度
        // 高度=左右两边的较小值，最宽的[0:n-1]不能保证最大水量，因为高度不是最高
        // 所以需要牺牲宽度，换取高度，从而使得整体最优
        // 双指针遍历左右两边，向中间缩小，得到更高的，时间复杂度O(n)
        // 何时向右移动，何时向左移动？ 两个柱子的min值决定了水量，所以移动较小的那个
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {

            int area = Math.min(height[left], height[right]) * (right - left);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
