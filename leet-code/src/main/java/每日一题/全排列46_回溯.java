package 每日一题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @hint 回溯
 */
public class 全排列46_回溯 {

    public static void main(String[] args) {
        List<List<Integer>> list = new 全排列46_回溯().permute(new int[]{1, 2, 3});
        list.forEach(l -> System.out.println(l.stream().map(String::valueOf).collect(Collectors.joining())));
    }

    private int N = 0;

    public List<List<Integer>> permute(int[] nums) {
        N = nums.length;
        List<List<Integer>> ans = new LinkedList<>();
        permute(nums, 0, ans);
        return ans;
    }

    // 对于当前已经排列的，和待排列的，分2个区 [a1, a2,...ak-1,  ak... aj...]
    // 如果后续使用bj，那么需要交换ak与aj，并扩充边界 k->k+1
    public void permute(int[] nums, int k, List<List<Integer>> ans) {

        //结束条件，边界到了结尾，收集结果
        if (k == N) {
            List<Integer> list = new ArrayList<>(N);
            for (int x = 0; x < N; x++) {
                list.add(nums[x]);
            }
            ans.add(list);
            return;
        }

        for (int j = k; j < N; j++) {
            //改变
            exch(nums, k, j);

            // 递归
            permute(nums, k + 1, ans);

            //还原
            exch(nums, k, j);
        }
    }


    private void exch(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
