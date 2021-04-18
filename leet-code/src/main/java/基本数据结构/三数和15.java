package 基本数据结构;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zhang Yi
 */
public class 三数和15 {

    public static void main(String[] args) {
        List<List<Integer>> list = threeSum(new int[] {-1, 0, 1, 2, -1, -4});
        for (List<Integer> e : list) {
            System.out.println(e);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        //将搜索问题转换为排序问题
        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<>();

        //固定一个
        for (int i = 0; i < nums.length; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //双指针查找
            int x = -nums[i];
            int a = i + 1, b = nums.length - 1;

            while (a < b) {
                int twoSum = nums[a] + nums[b];
                if (twoSum > x) {
                    b--;
                } else if (twoSum < x) {
                    a++;
                } else {
                    list.add(Arrays.asList(nums[i], nums[a], nums[b]));
                    b--;
                    a++;
                    //去重
                    while (a < b && nums[a + 1] == nums[a]) {
                        a++;
                    }
                    while (a < b && nums[b - 1] == nums[b]) {
                        b--;
                    }
                }
            }
        }

        return list;
    }
}
