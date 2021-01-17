package nSum问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 * <pre>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 三数之和15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //先对数组排序
        //暴力解法：三重for循环 a + b + c分别对a,b,c枚举，O(n^3)
        //由于b和c的枚举，实际上是并列的，对于一个确切的a：b与c的枚举是可以用2sum的双指针来解决，N*O(N) = O(N^2)

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            int a = nums[i];
            //b和c在a的右侧子区间
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int b = nums[lo];
                int c = nums[hi];
                int sum = a + b + c;
                //双指针lo,hi，lo++朝右侧单向移动, hi--朝左侧单向移动
                if (sum < 0) {
                    while (lo < hi && nums[lo] == b) { lo++; }
                } else if (sum > 0) {
                    while (lo < hi && nums[hi] == c) { hi--; }
                } else {
                    res.add(Arrays.asList(a, b, c));
                    while (lo < hi && nums[lo] == b) { lo++; }
                    while (lo < hi && nums[hi] == c) { hi--; }
                }
            }
            //while去重
            while (i < nums.length && nums[i] == a) { i++; }

        }
        return res;
    }
}
