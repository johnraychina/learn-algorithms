package 每日一题;

/**
 * <pr>
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * </pr>
 *
 * @author Zhang Yi
 * @hint 动态规划
 * @hint 滑动窗口
 */
public class 接雨水42 {
    public static void main(String[] args) {
//        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = new int[]{2, 0, 2};
        System.out.println(trap(height));
    }

    // 动态规划：计算每个位置的水量

    //双指针+滑动窗口
    // 经过观察，雨水多了会溢出来，所以决定水量的
    // 是最高和第二高柱子水量：第一高与左边第二高的柱子 + 第一高与 右边第二高的柱子
    // 我们发现这个过程是可以递归分治

    // 先从[0, n-1]区间中找到最高位置 p, 分别统计左边和右边的雨水
    // 向左查找：找到左边比p小的最高位置 p_left,  统计[p_left:p] 雨水
    // 然后令 p = p_left, 向左递归统计[0:p_left]
    // 向右查找：找到右边比p小的最高位置 p_right, 统计[p: p_right]  的雨水...
    // 然后令 p = p_right, 向右递归统计[p_right:n-1]

    // 我们将这个遍历过程反过来看：两个指针，左边第一个柱子，p_left, 右边第一个柱子 p_right
    // 在p_left右侧找到第一个大于等于它的柱子p_left_new，计算雨水量，加入总量，更新p_left，没找到 p_left则停止向右移动，
    // p_right向左移动到第一个大于等于它的柱子p_right_new，计算雨水量，加入总量，更新p_right，没找到 p_right则停止向右移动，
    // 如果  p_left, p_right 相遇（在最高峰）则停止计算
    public static int trap(int[] height) {
        int n = height.length;
        int p_left = 0;
        int p_right = n - 1;
        int total = 0;

        while (p_left < n) {
            // find first p_left_new that height[p_left_new]>= height[p_left]
            // calculate water between[p_left, p_left_new) = height*width - sum of height[p_left:p_left_new-1]
            int p_left_new = p_left + 1;
            int sum_pillar = 0;//柱子高度和
            while (p_left_new < n && height[p_left] > height[p_left_new]) {
                sum_pillar += height[p_left_new];
                p_left_new++;
            }
            // two reason to break previous while: out of bounds; find a height[p_left_new]>= height[p_left]
            if (p_left_new >= n) break;

            int water = height[p_left] * (p_left_new - p_left - 1) - sum_pillar;
            total += water;

            //slide to right
            p_left = p_left_new;

        }

        while (p_right > p_left) {
            int p_right_new = p_right - 1;
            int sum_pillar = 0;//柱子高度和
            while (p_right_new >= p_left && height[p_right] > height[p_right_new]) {
                sum_pillar += height[p_right_new];
                p_right_new--;
            }

            // two reason to break previous while: out of bounds; find a height[p_right_new]>= height[p_right]
            if (p_right_new < p_left) break;

            int water = height[p_right] * (p_right - p_right_new - 1) - sum_pillar;
            total += water;

            //slide to left
            p_right = p_right_new;
        }

        return total;
    }
}
