package 剑指offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhang Yi
 */
public class 数组中的重复数字 {

    public int findRepeatNumber(int[] nums) {

        //search problem
        //统计出现次数，不需要统计全部，只需要count>=2即可
        return sortAndSearch(nums);

    }

    private int sortAndSearch(int[] nums) {
        //思路2：
        //遍历数组：对第i个数字，搜索i+1~n-1范围内是否存在这个数字
        //改进：搜索问题归纳为排序问题，先用MergeSort排序时间是O(NlogN)，然后遍历数组，如果下一个等于上一个，则返回这个重复数字
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    public int hashSearch(int[] nums) {
        //思路1：
        //简单：暴力统计每个数字，放hash，当get发现有数字则返回这个重复数字
        //改进：遍历数组，尝试add到set，如果发现set中已经有这个数字，则返回它
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }

        return -1;
    }

}
