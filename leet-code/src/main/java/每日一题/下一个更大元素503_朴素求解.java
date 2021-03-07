package 每日一题;

/**
 * @author Zhang Yi
 */
public class 下一个更大元素503_朴素求解 {

    public int[] nextGreaterElements(int[] nums) {

        int N = nums.length;
        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {

            //默认值-1
            ans[i] = -1;
            //找到下一个更大值则覆盖默认值
            for (int j = i + 1; j < i + N; j++) {
                int x = j % N;
                if (nums[x] > nums[i]) {
                    ans[i] = nums[x];
                    break;
                }
            }
        }

        return ans;
    }
}
